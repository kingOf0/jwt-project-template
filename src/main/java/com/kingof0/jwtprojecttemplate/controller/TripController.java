package com.kingof0.jwtprojecttemplate.controller;

import com.kingof0.jwtprojecttemplate.model.dto.trip.TripDto;
import com.kingof0.jwtprojecttemplate.model.entity.trip.Trip;
import com.kingof0.jwtprojecttemplate.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trip")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @GetMapping("/all")
    public List<Trip> getAllTrips() {
        return tripService.findAll();
    }

    @GetMapping("/byRoute/{route}")
    public TripDto getTripByRoute(@PathVariable String route) {
        return tripService.findByRoute(route);
    }

    @GetMapping("/byCategory/{route}")
    public List<TripDto> getTripsByCategory(@PathVariable String route) {
        return tripService.findByCategory(route);
    }

    @GetMapping("/random")
    public List<TripDto> getRandomTrips(@RequestParam int count) {
        return tripService.getRandomTrips(count);
    }

}
