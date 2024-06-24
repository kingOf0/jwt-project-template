package com.kingof0.jwtprojecttemplate.service;

import com.kingof0.jwtprojecttemplate.model.entity.trip.TripRoom;
import com.kingof0.jwtprojecttemplate.repository.trip.TripRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripPriceService {

    private final TripRoomRepository tripRoomRepository;

    public TripRoom save(TripRoom tripRoom) {
        return tripRoomRepository.save(tripRoom);
    }

}
