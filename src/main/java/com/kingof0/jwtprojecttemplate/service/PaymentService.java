package com.kingof0.jwtprojecttemplate.service;

import com.iyzipay.model.*;
import com.iyzipay.request.CreatePaymentRequest;
import com.kingof0.jwtprojecttemplate.model.dto.checkout.CheckoutDto;
import com.kingof0.jwtprojecttemplate.model.dto.payment.PaymentCardDto;
import com.kingof0.jwtprojecttemplate.model.entity.customer.BasicAddress;
import com.kingof0.jwtprojecttemplate.model.entity.customer.BillAddress;
import com.kingof0.jwtprojecttemplate.model.entity.customer.CustomerContact;
import com.kingof0.jwtprojecttemplate.model.entity.payment.Payment;
import com.kingof0.jwtprojecttemplate.model.entity.payment.TripRoomCheckout;
import com.kingof0.jwtprojecttemplate.repository.CustomerContactRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final CustomerContactRepository customerContactRepository;

    public CreatePaymentRequest createPaymentRequest(Payment checkout, CheckoutDto checkoutDto, HttpServletRequest servletRequest) {
        CreatePaymentRequest request = new CreatePaymentRequest();
        request.setLocale(Locale.TR.getValue());
        request.setConversationId(checkout.getId().toString());
        request.setPrice(BigDecimal.valueOf(checkout.getTotalPrice()));
        request.setPaidPrice(BigDecimal.valueOf(checkout.getTotalPrice()));
        request.setCurrency(Currency.TRY.name());
        request.setInstallment(1);
        request.setBasketId("B" + checkout.getId());
        request.setPaymentChannel(PaymentChannel.WEB.name());
        request.setPaymentGroup(PaymentGroup.PRODUCT.name());

        request.setPaymentCard(createPaymentCard(checkoutDto.getPaymentCard()));
        request.setBuyer(createBuyer(checkoutDto.getContact(), servletRequest));
        request.setShippingAddress(createShippingAddress(checkoutDto));
        request.setBillingAddress(createBillingAddress(checkoutDto));
        request.setBasketItems(createBasketItems(checkout));
        return request;
    }

    private List<BasketItem> createBasketItems(Payment payment) {
        BasketItem item = new BasketItem();

        for (TripRoomCheckout roomCheckout : payment.getRoomCheckouts()) {
            item.setId(roomCheckout.getTripRoom().getId().toString());
            item.setName(roomCheckout.getTripRoom().getName());
            item.setCategory1("Room");
            item.setItemType(BasketItemType.PHYSICAL.name());
            item.setPrice(BigDecimal.valueOf(roomCheckout.getTripRoom().getPrice()));
        }

        return List.of(item);
    }

    private PaymentCard createPaymentCard(PaymentCardDto paymentCard) {
        PaymentCard card = new PaymentCard();

        card.setCardHolderName(paymentCard.getCardHolder());

        card.setCardNumber(paymentCard.getCardNumber());
        card.setExpireMonth(paymentCard.getExpireMonth());
        card.setExpireYear(paymentCard.getExpireYear());
        card.setCvc(paymentCard.getCvc());
        card.setRegisterCard(0);

        return card;
    }

    private Address createShippingAddress(CheckoutDto checkoutDto) {
        CustomerContact contact = checkoutDto.getContact();
        BasicAddress basicAddress = contact.getAddress();

        Address address = new Address();

        address.setContactName(contact.getFirstName() + " " + contact.getLastName());
        address.setCity(basicAddress.getCity());
        address.setCountry(basicAddress.getCountry());
        address.setAddress(basicAddress.getAddress());

        return address;
    }

    private Address createBillingAddress(CheckoutDto checkoutDto) {
        CustomerContact contact = checkoutDto.getContact();
        BillAddress billAddress = contact.getBillAddress();

        Address address = new Address();

        address.setContactName(contact.getFirstName() + " " + contact.getLastName());
        address.setCity(billAddress.getCity());
        address.setCountry(billAddress.getCountry());
        address.setAddress(billAddress.getAddress());

        return address;
    }

    private Buyer createBuyer(CustomerContact customerContact, HttpServletRequest servletRequest) {
        Buyer buyer = new Buyer();

        CustomerContact savedCustomerContact = customerContactRepository.save(customerContact);

        buyer.setId(savedCustomerContact.getId().toString());
        buyer.setName(savedCustomerContact.getFirstName());
        buyer.setSurname(savedCustomerContact.getLastName());
        buyer.setGsmNumber(savedCustomerContact.getPhone());
        buyer.setEmail(savedCustomerContact.getEmail());
        buyer.setIdentityNumber(savedCustomerContact.getTurkishIdNumber());
        buyer.setIp(servletRequest.getRemoteAddr());

        BasicAddress address = savedCustomerContact.getAddress();
        buyer.setCity(address.getCity());
        buyer.setCountry(address.getCountry());
        buyer.setRegistrationAddress(address.getAddress());

        return buyer;
    }


}
