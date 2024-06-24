package com.kingof0.jwtprojecttemplate.model.mapper;

import com.kingof0.jwtprojecttemplate.model.dto.trip.TripDetailsDto;
import com.kingof0.jwtprojecttemplate.model.dto.trip.TripDto;
import com.kingof0.jwtprojecttemplate.model.entity.trip.Trip;
import com.kingof0.jwtprojecttemplate.model.entity.trip.TripDetails;
import com.kingof0.jwtprojecttemplate.repository.trip.TripRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TripMapper {

    private final TripRoomMapper tripRoomMapper;
    private final TripRoomRepository tripRoomRepository;

    public TripDto toDto(Trip trip) {
        TripDto tripDto = new TripDto();

        tripDto.setId(trip.getId());

        tripDto.setTitle(trip.getTitle());
        tripDto.setSubtitle(trip.getSubtitle());
        tripDto.setDescription(trip.getDescription());
        tripDto.setRoute(trip.getRoute());

        tripDto.setImages(trip.getImages());

        tripDto.setQuota(trip.getQuota());
        tripDto.setDuration(trip.getDuration());

        tripDto.setStartDate(trip.getStartDate());
        tripDto.setEndDate(trip.getEndDate());

        tripDto.setLocation(trip.getLocation());
        tripDto.setCategory(trip.getCategory());
        tripDto.setDetails(toTripDetailsDto(trip.getDetails()));
        tripDto.setComments(trip.getComments());
        tripDto.setInformation(trip.getInformations());

        tripDto.setPrice(trip.getPrice());
        tripDto.setStrikethroughPrice(trip.getStrikethroughPrice());

        tripDto.setCurrencyType(trip.getCurrencyType());

        tripDto.setTripRooms(tripRoomMapper.toDtoList(tripRoomRepository.findAllByTripId(trip.getId())));

        return tripDto;
    }

    private TripDetailsDto toTripDetailsDto(TripDetails details) {
        TripDetailsDto tripDetailsDto = new TripDetailsDto();

        tripDetailsDto.setIncludedServices(details.getIncludedServices());
        tripDetailsDto.setExcludedServices(details.getExcludedServices());
        tripDetailsDto.setFaqs(details.getFaqs());
        tripDetailsDto.setMapImage(details.getMapImage());

        return tripDetailsDto;
    }


    public List<TripDto> toDtoList(List<Trip> tripList) {
        return tripList.stream().map(this::toDto).toList();
    }
}
