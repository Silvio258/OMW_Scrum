package com.example.omway.dto.omwUser;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DriverDto {
    private String driverCif;
    private String password;
    private String name;
    private String phone;
    private String email;
    private boolean state;

    //Driver
    private LocalDate dlExpirationDate;
}
