package com.kingof0.jwtprojecttemplate.model.dto.trip;

import com.kingof0.jwtprojecttemplate.model.entity.CurrencyType;
import com.kingof0.jwtprojecttemplate.model.entity.UploadedFile;
import com.kingof0.jwtprojecttemplate.model.entity.trip.TripCategory;
import com.kingof0.jwtprojecttemplate.model.entity.trip.TripComment;
import com.kingof0.jwtprojecttemplate.model.entity.trip.TripInformation;
import com.kingof0.jwtprojecttemplate.model.entity.trip.TripLocation;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class TripDto {

    private Long id;

    private String title;
    private String subtitle;
    private String description;
    private String route;

    private List<UploadedFile> images;
    private List<TripRoomDto> tripRooms;
    private List<TripComment> comments;
    private List<TripInformation> information;

    private Integer quota;
    private Integer duration;

    private LocalDate startDate;
    private LocalDate endDate;

    private TripLocation location;
    private TripCategory category;

    private TripDetailsDto details;


    private Double price;
    private Double strikethroughPrice;

    private CurrencyType currencyType;

}
