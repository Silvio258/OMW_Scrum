package com.example.omway.service.vehicle;
import com.example.omway.dto.vehicle.CarDto;
import com.example.omway.model.vehicle.Car;

import org.springframework.stereotype.Service;

@Service
public interface IServiceCar {
    public Car save(CarDto carDto);

    public void delete(String id);

}
