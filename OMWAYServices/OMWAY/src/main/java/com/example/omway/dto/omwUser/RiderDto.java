package com.example.omway.dto.omwUser;

import lombok.Data;


@Data
public class RiderDto {
    private String riderCif;
    private String password;
    private String name;
    private String phone;
    private String email;
    private boolean state;
}
