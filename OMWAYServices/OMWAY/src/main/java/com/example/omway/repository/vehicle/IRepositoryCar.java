package com.example.omway.repository.vehicle;

import com.example.omway.model.vehicle.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryCar extends JpaRepository<Car,String> {
}
