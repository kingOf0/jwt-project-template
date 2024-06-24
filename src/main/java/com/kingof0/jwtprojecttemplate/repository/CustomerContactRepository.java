package com.kingof0.jwtprojecttemplate.repository;

import com.kingof0.jwtprojecttemplate.model.entity.customer.Customer;
import com.kingof0.jwtprojecttemplate.model.entity.customer.CustomerContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerContactRepository extends JpaRepository<CustomerContact, Long> {
}
