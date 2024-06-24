package com.kingof0.jwtprojecttemplate.model.dto.checkout;

import com.kingof0.jwtprojecttemplate.model.dto.payment.PaymentCardDto;
import com.kingof0.jwtprojecttemplate.model.dto.trip.TripRoomCheckoutDto;
import com.kingof0.jwtprojecttemplate.model.entity.customer.CustomerContact;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CheckoutDto {

    private Long tripId;

    private List<TripRoomCheckoutDto> roomCheckouts;

    private PaymentCardDto paymentCard;

    private CustomerContact contact;

}
