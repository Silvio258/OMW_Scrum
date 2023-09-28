package com.example.omway.controller.vehicle;

import com.example.omway.dto.vehicle.CarDto;
import com.example.omway.model.vehicle.Car;
import com.example.omway.service.vehicle.IServiceCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
public class ControllerCar {
    @Autowired
    private IServiceCar serviceCar;

    @PostMapping("/save")
    public Car save(@RequestBody CarDto carDto){
        return serviceCar.save(carDto);
    }

    @PutMapping("/update")
    public Car update(@RequestBody CarDto carDto) throws Exception {
        if(carDto.getLicensePlate()==null){
            throw new Exception("Please, send the Id value");
        }
        return serviceCar.save(carDto);
    }

    @DeleteMapping(value="/delete/{id}")
    public void delete(@PathVariable String id) {
        serviceCar.delete(id);
    }
}
