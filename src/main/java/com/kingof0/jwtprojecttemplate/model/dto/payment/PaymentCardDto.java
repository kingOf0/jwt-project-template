package com.kingof0.jwtprojecttemplate.model.dto.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentCardDto {

    private String cardHolder;
    private String cardNumber;
    private String expireYear;
    private String expireMonth;
    private String cvc;

}
