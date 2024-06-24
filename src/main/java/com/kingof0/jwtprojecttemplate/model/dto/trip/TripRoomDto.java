package com.kingof0.jwtprojecttemplate.model.dto.trip;

import com.kingof0.jwtprojecttemplate.model.entity.CurrencyType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TripRoomDto {

    private Long id;

    private String name;

    private Double price;

    private CurrencyType currencyType;

    private Integer guestCount;

}
