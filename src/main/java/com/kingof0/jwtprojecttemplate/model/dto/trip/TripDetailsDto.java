package com.kingof0.jwtprojecttemplate.model.dto.trip;

import com.kingof0.jwtprojecttemplate.model.entity.UploadedFile;
import com.kingof0.jwtprojecttemplate.model.entity.trip.TripFAQ;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TripDetailsDto {

    private List<String> includedServices;

    private List<String> excludedServices;

    private List<TripFAQ> faqs;

    private UploadedFile mapImage;

}
