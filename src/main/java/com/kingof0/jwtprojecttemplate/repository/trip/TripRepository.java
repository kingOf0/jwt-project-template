package com.kingof0.jwtprojecttemplate.repository.trip;

import com.kingof0.jwtprojecttemplate.model.entity.trip.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {


    Trip findByRoute(String route);

    List<Trip> findAllByCategoryRoute(String route);
}
