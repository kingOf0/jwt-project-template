package com.kingof0.jwtprojecttemplate.model.dto.checkout;

import com.kingof0.jwtprojecttemplate.model.entity.payment.PaymentState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckoutResponseDto {

    private String token;
    private Long paymentId;
    private PaymentState status;

}
