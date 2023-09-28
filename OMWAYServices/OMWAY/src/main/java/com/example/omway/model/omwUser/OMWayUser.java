package com.example.omway.model.omwUser;

import com.example.omway.model.trip.Ride;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@MappedSuperclass
@Data
public abstract class OMWayUser {
    @Id
    private String cif;

    private String password;
    private String name;
    private String phone;
    private String email;
    private boolean state;

}
