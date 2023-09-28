package com.example.omway.model.vehicle;

import com.example.omway.model.omwUser.Driver;
import com.example.omway.model.trip.Ride;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name="Car",schema = "Vehicle")
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name="Car.all",query="select e from Car e")
})
public class Car {
    @Id
    private String licensePlate;

    private String color;
    private String year;
    private boolean state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey=@ForeignKey(name="FK_driver_car_drivercif"))
    @JsonIgnore
    private Driver driver;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey=@ForeignKey(name="FK_model_car_modelid"))
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Model model;

    @OneToMany(mappedBy = "car",cascade = CascadeType.ALL
            ,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Ride> ride;
}
