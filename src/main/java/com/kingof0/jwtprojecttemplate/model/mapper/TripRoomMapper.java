package com.kingof0.jwtprojecttemplate.model.mapper;

import com.kingof0.jwtprojecttemplate.model.dto.trip.TripRoomDto;
import com.kingof0.jwtprojecttemplate.model.entity.trip.TripRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TripRoomMapper {

    public TripRoomDto toDto(TripRoom tripRoom) {
        TripRoomDto tripRoomDto = new TripRoomDto();

        tripRoomDto.setId(tripRoom.getId());

        tripRoomDto.setName(tripRoom.getName());
        tripRoomDto.setPrice(tripRoom.getPrice());
        tripRoomDto.setCurrencyType(tripRoom.getCurrencyType());
        tripRoomDto.setGuestCount(tripRoom.getGuestCount());

        return tripRoomDto;
    }

    public TripRoom toEntity(TripRoomDto tripRoomDto) {
        TripRoom tripRoom = new TripRoom();

        tripRoom.setId(tripRoomDto.getId());

        tripRoom.setName(tripRoomDto.getName());
        tripRoom.setPrice(tripRoomDto.getPrice());
        tripRoom.setCurrencyType(tripRoomDto.getCurrencyType());
        tripRoom.setGuestCount(tripRoomDto.getGuestCount());

        return tripRoom;
    }

    public List<TripRoomDto> toDtoList(List<TripRoom> allByTripId) {
        return allByTripId.stream().map(this::toDto).toList();
    }

    public List<TripRoom> toEntityList(List<TripRoomDto> allByTripId) {
        return allByTripId.stream().map(this::toEntity).toList();
    }

}
