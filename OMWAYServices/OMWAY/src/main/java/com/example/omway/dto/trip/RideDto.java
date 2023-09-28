package com.example.omway.dto.trip;

import com.example.omway.model.trip.RideState;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;

@Data
public class RideDto {
    private Integer id;
    private Time pickUpTime;
    private Time dropOffTime;
    private String pickUpLocation;
    private String dropOffLocation;
    private Double distance;
    private LocalDate date;
    private String notes;
    private RideState state;
    private Double fare;
    private int rating;
    private String comment;
    private String riderId;
    private String driverId;
    private String carId;
}
