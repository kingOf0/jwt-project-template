package com.kingof0.jwtprojecttemplate.repository.trip;

import com.kingof0.jwtprojecttemplate.model.entity.trip.TripRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRoomRepository extends JpaRepository<TripRoom, Long> {
    List<TripRoom> findAllByTripId(Long id);
}
