package com.kingof0.jwtprojecttemplate.controller;

import com.kingof0.jwtprojecttemplate.model.dto.checkout.CheckoutDto;
import com.kingof0.jwtprojecttemplate.model.dto.payment.PaymentResultDto;
import com.kingof0.jwtprojecttemplate.service.CheckoutService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {

    private final CheckoutService checkoutService;

    @PostMapping("/checkout")
    public PaymentResultDto checkout(@RequestBody CheckoutDto checkout, HttpServletRequest servletRequest) {
        return checkoutService.checkout(checkout, servletRequest);
    }

    @GetMapping("/result")
    public PaymentResultDto result(@RequestParam String paymentId, @RequestParam String token) {
        return checkoutService.result(paymentId, token);
    }


}
