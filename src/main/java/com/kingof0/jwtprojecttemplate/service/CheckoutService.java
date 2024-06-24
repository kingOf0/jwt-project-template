package com.kingof0.jwtprojecttemplate.service;

import com.iyzipay.model.Status;
import com.iyzipay.request.CreatePaymentRequest;
import com.kingof0.jwtprojecttemplate.config.PaymentConfiguration;
import com.kingof0.jwtprojecttemplate.exception.AppException;
import com.kingof0.jwtprojecttemplate.model.dto.checkout.CheckoutDto;
import com.kingof0.jwtprojecttemplate.model.dto.checkout.CheckoutResponseDto;
import com.kingof0.jwtprojecttemplate.model.dto.payment.PaymentResultDto;
import com.kingof0.jwtprojecttemplate.model.dto.trip.TripRoomCheckoutDto;
import com.kingof0.jwtprojecttemplate.model.entity.payment.Payment;
import com.kingof0.jwtprojecttemplate.model.entity.payment.PaymentState;
import com.kingof0.jwtprojecttemplate.model.entity.payment.TripRoomCheckout;
import com.kingof0.jwtprojecttemplate.model.entity.trip.Trip;
import com.kingof0.jwtprojecttemplate.model.entity.trip.TripRoom;
import com.kingof0.jwtprojecttemplate.model.mapper.CustomerMapper;
import com.kingof0.jwtprojecttemplate.repository.CheckoutRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class CheckoutService {

    private final PaymentService paymentService;
    private final CheckoutRepository checkoutRepository;
    private final TripService tripService;
    private final PaymentConfiguration paymentConfiguration;
    private final CustomerMapper customerMapper;


    public PaymentResultDto checkout(CheckoutDto checkoutDto, HttpServletRequest servletRequest) {
        Trip trip = tripService.getTripById(checkoutDto.getTripId());

        List<TripRoomCheckout> roomCheckouts = new ArrayList<>();
        Integer guestCount = 0;
        Double totalPrice = 0.0;
        for (TripRoomCheckoutDto roomCheckoutDto : checkoutDto.getRoomCheckouts()) {
            TripRoom tripRoom = tripService.getTripPriceById(roomCheckoutDto.getId());
            if (!Objects.equals(tripRoom.getTrip().getId(), trip.getId())) {
                throw new AppException("Trip price does not belong to the trip", 400);
            }
            guestCount += tripRoom.getGuestCount();
            totalPrice += tripRoom.getPrice();

            TripRoomCheckout tripRoomCheckout = new TripRoomCheckout();
            tripRoomCheckout.setTripRoom(tripRoom);
            tripRoomCheckout.setCustomers(customerMapper.toCustomerList(roomCheckoutDto.getCustomers()));

            roomCheckouts.add(tripRoomCheckout);
        }

        /*
        if (guestCount != checkoutDto.getCustomers().size()){
            throw new AppException("Guest count does not match with the trip price", 400);
        }
        */

        Payment payment = new Payment();
        payment.setRoomCheckouts(roomCheckouts);
        payment.setTrip(trip);
        payment.setTotalPrice(totalPrice);
        payment.setContact(checkoutDto.getContact());
        payment.setStatus(PaymentState.PAYMENT_REQUEST);
        payment.setCreatedAt(LocalDateTime.now());

        CreatePaymentRequest request = paymentService.createPaymentRequest(checkoutRepository.save(payment), checkoutDto, servletRequest);

        com.iyzipay.model.Payment iyzicoPayment = com.iyzipay.model.Payment.create(request, paymentConfiguration.getOptions());

        if (!iyzicoPayment.getStatus().equals(Status.SUCCESS.getValue())) {
            Logger.getGlobal().info("Payment failed: " + iyzicoPayment.getErrorMessage());
            throw new AppException("Payment failed", 400);
        }

        payment.setStatus(PaymentState.PAYMENT_SUCCESS);
        payment.setToken(createToken());

        Payment savedPayment = checkoutRepository.save(payment);

        return toPaymentResponseDto(savedPayment);
    }

    private CheckoutResponseDto toCheckoutResponseDto(Payment savedPayment) {
        CheckoutResponseDto checkoutResponseDto = new CheckoutResponseDto();
        checkoutResponseDto.setPaymentId(savedPayment.getId());
        checkoutResponseDto.setToken(savedPayment.getToken());
        checkoutResponseDto.setStatus(savedPayment.getStatus());
        return checkoutResponseDto;
    }

    private static final String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private String createToken() {
        StringBuilder token = new StringBuilder();

        for (int i = 0; i < 16; i++) {
            if (i % 4 == 0 && i != 0) {
                token.append("-");
            }
            if (i % 2 == 0) {
                token.append(alphabets.charAt((int) (Math.random() * alphabets.length())));
            } else {
                token.append((int) (Math.random() * 10));
            }
        }

        return token.toString();
    }

    public PaymentResultDto result(String paymentId, String token) {
        Payment payment = checkoutRepository.findById(Long.parseLong(paymentId)).orElseThrow(() -> new AppException("Payment not found", 404));

        if (!payment.getToken().equals(token)) {
            throw new AppException("Token does not match", 400);
        }
        if (!payment.getStatus().equals(PaymentState.PAYMENT_SUCCESS)) {
            throw new AppException("Payment is not successful", 400);
        }
        if (payment.getCreatedAt().plusDays(14).isBefore(LocalDateTime.now())) {
            throw new AppException("Payment is expired", 400);
        }

        return toPaymentResponseDto(payment);
    }

    private PaymentResultDto toPaymentResponseDto(Payment payment) {
        PaymentResultDto paymentResultDto = new PaymentResultDto();
        paymentResultDto.setTrip(payment.getTrip());
        paymentResultDto.setRoomCheckouts(payment.getRoomCheckouts());
        paymentResultDto.setContact(payment.getContact());
        paymentResultDto.setCreatedAt(payment.getCreatedAt());
        paymentResultDto.setPaymentId(payment.getId());
        paymentResultDto.setStatus(payment.getStatus());
        paymentResultDto.setToken(payment.getToken());
        paymentResultDto.setTotalPrice(payment.getTotalPrice());
        return paymentResultDto;
    }


}
