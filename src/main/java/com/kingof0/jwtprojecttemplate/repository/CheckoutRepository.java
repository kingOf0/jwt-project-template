package com.kingof0.jwtprojecttemplate.repository;

import com.kingof0.jwtprojecttemplate.model.entity.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckoutRepository extends JpaRepository<Payment, Long> {
}
