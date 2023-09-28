package com.example.omway.service.trip;

import com.example.omway.dto.trip.RideDto;
import com.example.omway.model.trip.Ride;
import com.example.omway.model.trip.RideState;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IServiceRide {
    public Ride save(RideDto rideDto);

    //Obtener viajes con estado REQUESTED, ACCEPTED,OMWAY,STARTED
    public List<Ride> findInProgressRidesByRiderCif(String cif);
    //Obtener viajes con estado FINISHED, CANCELED BY DRIVER, CANCELED BY RIDER
    public List<Ride> findDiscontinuedRidesByRiderCif(String cif);

    public List<Ride> findInProgressRidesByDriverCif(String cif);
    //Obtener viajes con estado FINISHED, CANCELED BY DRIVER, CANCELED BY RIDER
    public List<Ride> findDiscontinuedRidesByDriverCif(String cif);

    //Obtener todos los viajes con estado REQUESTED
    public List<Ride> getRequestedRides();


}
