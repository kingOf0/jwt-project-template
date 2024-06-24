package com.kingof0.jwtprojecttemplate.service;

import com.kingof0.jwtprojecttemplate.exception.AppException;
import com.kingof0.jwtprojecttemplate.model.dto.trip.TripDto;
import com.kingof0.jwtprojecttemplate.model.entity.trip.Trip;
import com.kingof0.jwtprojecttemplate.model.entity.trip.TripRoom;
import com.kingof0.jwtprojecttemplate.model.mapper.TripMapper;
import com.kingof0.jwtprojecttemplate.repository.trip.TripRoomRepository;
import com.kingof0.jwtprojecttemplate.repository.trip.TripRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final TripRoomRepository tripRoomRepository;
    private final TripMapper tripMapper;

    public void save(Trip trip) {
        tripRepository.save(trip);
    }

    @Transactional
    public void delete(Long id) {
        tripRepository.deleteById(id);
    }

    public Trip findById(Long id) {
        return tripRepository.findById(id).orElse(null);
    }

    public List<Trip> findAll() {
        return tripRepository.findAll();
    }


    public Trip getTripById(Long tripId) {
        return tripRepository.findById(tripId).orElseThrow(() -> new AppException("Trip not found", 404));
    }

    public TripRoom getTripPriceById(Long tripPriceId) {
        return tripRoomRepository.findById(tripPriceId).orElseThrow(() -> new AppException("Trip price not found", 404));
    }

    public TripDto findByRoute(String route) {
        return tripMapper.toDto(tripRepository.findByRoute(route));
    }

    public List<TripDto> findByCategory(String route) {
        return tripMapper.toDtoList(tripRepository.findAllByCategoryRoute(route));
    }

    public List<TripDto> getRandomTrips(int count) {
        List<Trip> tripList = tripRepository.findAll();

        List<TripDto> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int randomIndex = (int) (Math.random() * tripList.size());
            result.add(tripMapper.toDto(tripList.get(randomIndex)));
        }

        return result;
    }
}
