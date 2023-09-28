package com.example.omway.model.payment;


import com.example.omway.model.trip.Ride;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;


//Clase payment
@Entity
@Data
@Table(name="Payment",schema = "Payment")
public class Payment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer paymentId;
    private double total;
    @Enumerated(EnumType.ORDINAL)
    private PaymentMethod paymentMethod;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rideId", referencedColumnName = "id")
    //@JsonManagedReference
   @JsonBackReference
    private Ride ride;

}
