package com.kingof0.jwtprojecttemplate.model.dto.payment;

import com.kingof0.jwtprojecttemplate.model.entity.customer.CustomerContact;
import com.kingof0.jwtprojecttemplate.model.entity.payment.PaymentState;
import com.kingof0.jwtprojecttemplate.model.entity.payment.TripRoomCheckout;
import com.kingof0.jwtprojecttemplate.model.entity.trip.Trip;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PaymentResultDto {

    private Trip trip;

    private PaymentState status;

    private List<TripRoomCheckout> roomCheckouts;

    private CustomerContact contact;

    private LocalDateTime createdAt;

    private Long paymentId;

    private Double totalPrice;

    private String token;

}
