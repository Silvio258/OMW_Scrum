package com.example.omway.model.omwUser;

import com.example.omway.model.trip.Ride;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@DiscriminatorValue("driver")
@Table(name="Rider",schema = "OMWUser")
public class Rider extends OMWayUser{

    @OneToMany(mappedBy = "rider",cascade = CascadeType.ALL
            ,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Ride> riderRides;


}
