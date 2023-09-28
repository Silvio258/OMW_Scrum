package com.example.omway.model.omwUser;

import com.example.omway.model.trip.Ride;
import com.example.omway.model.vehicle.Car;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@DiscriminatorValue("driver")
@Table(name="Driver",schema = "OMWUser")

public class Driver extends OMWayUser {
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate dlExpirationDate;
    private int sumRating;

    private int numberRides;

    @OneToMany(mappedBy = "driver",cascade = CascadeType.ALL
            ,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Car> driverCars;

    @OneToMany(mappedBy = "driver",cascade = CascadeType.ALL
            ,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Ride> driverRides;



}
