package com.example.omway.service.trip;

import com.example.omway.dto.trip.RideDto;
import com.example.omway.model.payment.Payment;
import com.example.omway.model.trip.Ride;
import com.example.omway.model.trip.RideState;
import com.example.omway.repository.omwUser.IRepositoryDriver;
import com.example.omway.repository.omwUser.IRepositoryRider;
import com.example.omway.repository.trip.IRepositoryRide;
import com.example.omway.repository.vehicle.IRepositoryCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceRide implements IServiceRide {

    @Autowired
    private IRepositoryRide repositoryRide;
    @Autowired
    private IRepositoryRider repositoryRider;
    @Autowired
    private IRepositoryDriver repositoryDriver;
    @Autowired
    private IRepositoryCar repositoryCar;

    @Override
    public List<Ride> findInProgressRidesByRiderCif(String cif) {
        List<RideState> states = new ArrayList<RideState>();
        states.add(RideState.REQUESTED);
        states.add(RideState.ACCEPTED);
        states.add(RideState.ONMYWAY);
        states.add(RideState.STARTED);
        List<Ride> rides = repositoryRide.findRidesByRiderCif(cif,states);
        return rides;
    }

    @Override
    public List<Ride> findDiscontinuedRidesByRiderCif(String cif){
        List<RideState> states = new ArrayList<RideState>();
        states.add(RideState.FINISHED);
        states.add(RideState.CANCELLEDBYDRIVER);
        states.add(RideState.CANCELLEDBYRIDER);
        List<Ride> rides = repositoryRide.findRidesByRiderCif(cif,states);
        return rides;
    }

    @Override
    public List<Ride> findInProgressRidesByDriverCif(String cif) {
        List<RideState> states = new ArrayList<RideState>();
        states.add(RideState.REQUESTED);
        states.add(RideState.ACCEPTED);
        states.add(RideState.ONMYWAY);
        states.add(RideState.STARTED);
        List<Ride> rides = repositoryRide.findRidesByDriverCif(cif,states);
        return rides;
    }

    @Override
    public List<Ride> findDiscontinuedRidesByDriverCif(String cif) {
        List<RideState> states = new ArrayList<RideState>();
        states.add(RideState.FINISHED);
        states.add(RideState.CANCELLEDBYDRIVER);
        states.add(RideState.CANCELLEDBYRIDER);
        List<Ride> rides = repositoryRide.findRidesByDriverCif(cif,states);
        return rides;
    }

    @Override
    public List<Ride> getRequestedRides() {
        return repositoryRide.getRequestedRides(RideState.REQUESTED);
    }

    @Override
    public Ride save(RideDto rideDto) {

        Optional<Ride> r1 = repositoryRide.findById(rideDto.getId());

        Ride r = new Ride();

        if (r1.isPresent()){
            r = r1.get();
            System.out.println("Esta presente, procedemos a actualizar");
        }
        System.out.println(rideDto.getDate());

        r.setPickUpTime(rideDto.getPickUpTime());
        r.setDropOffTime(rideDto.getDropOffTime());
        r.setPickUpLocation(rideDto.getPickUpLocation());
        r.setDropOffLocation(rideDto.getDropOffLocation());
        r.setDistance(rideDto.getDistance());
        r.setDate(rideDto.getDate());
        r.setNotes(rideDto.getNotes());
        r.setState(rideDto.getState());
        r.setFare(rideDto.getFare());
        r.setRating(rideDto.getRating());
        r.setComment(rideDto.getComment());
        r.setRider(
                repositoryRider.findById(rideDto.getRiderId()).get()
                );


        if(rideDto.getDriverId()!=null){
            r.setDriver(
                    repositoryDriver.findById(rideDto.getDriverId()).get()
            );
        }
        if(rideDto.getCarId()!=null){
            r.setCar(
                    repositoryCar.findById(rideDto.getCarId()).get()
            );
        }

        return repositoryRide.save(r);
    }


}
