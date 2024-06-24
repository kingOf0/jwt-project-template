package com.kingof0.jwtprojecttemplate.model.dto.trip;

import com.kingof0.jwtprojecttemplate.model.dto.checkout.CustomerDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TripRoomCheckoutDto {

    private Long id; //TripRoom id

    private List<CustomerDto> customers;

}
