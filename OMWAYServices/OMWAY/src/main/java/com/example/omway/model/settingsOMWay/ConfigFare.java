package com.example.omway.model.settingsOMWay;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Configfare",schema = "Settings")
public class ConfigFare {
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private double fare;
    private String name;

    private boolean state;

}
