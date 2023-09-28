package com.example.omway.dto.vehicle;
import lombok.Data;

@Data
public class CarDto {
    private String licensePlate;
    private String color;
    private String year;
    private boolean state;
    private String driverId;
    private Integer modelId;
}
