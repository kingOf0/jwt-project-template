package com.kingof0.jwtprojecttemplate.model.mapper;

import com.kingof0.jwtprojecttemplate.model.dto.checkout.CustomerDto;
import com.kingof0.jwtprojecttemplate.model.entity.customer.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerMapper {

    public List<CustomerDto> toCustomerDtoList(List<Customer> customerList) {
        if (customerList == null) return null;

        return customerList.stream().map(this::toCustomerDto).toList();
    }

    public List<Customer> toCustomerList(List<CustomerDto> customerDtoList) {
        if (customerDtoList == null) return null;

        return new ArrayList<>(customerDtoList.stream().map(this::toCustomer).toList());
    }

    private Customer toCustomer(CustomerDto customerDto) {
        if (customerDto == null) return null;

        Customer customer = new Customer();

        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setTurkishIdNumber(customerDto.getTurkishIdNumber());
        customer.setGender(customer.getGender());
        customer.setBirthDate(customerDto.getBirthDate());

        return customer;
    }

    private CustomerDto toCustomerDto(Customer customer) {
        if (customer == null) return null;

        CustomerDto customerDto = new CustomerDto();

        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setTurkishIdNumber(customer.getTurkishIdNumber());
        customerDto.setGender(customer.getGender());
        customerDto.setBirthDate(customer.getBirthDate());

        return customerDto;
    }

}
