package com.kingof0.jwtprojecttemplate.repository;

import com.kingof0.jwtprojecttemplate.model.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
