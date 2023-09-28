package com.example.omway.service.vehicle;


import com.example.omway.dto.vehicle.CarDto;
import com.example.omway.model.omwUser.Driver;
import com.example.omway.model.vehicle.Car;
import com.example.omway.model.vehicle.Model;
import com.example.omway.repository.omwUser.IRepositoryDriver;
import com.example.omway.repository.vehicle.IRepositoryCar;
import com.example.omway.repository.vehicle.IRepositoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@Component("ServiceCar")
public class ServiceCar implements IServiceCar {
    @Autowired
    private IRepositoryCar repositoryCar;
    @Autowired
    private IRepositoryModel repositoryModel;
    @Autowired
    private IRepositoryDriver repositoryDriver;
    @Override
    public Car save(CarDto carDto) {
        Optional<Car> c1 = repositoryCar.findById(carDto.getLicensePlate());
        Car c = new Car();
        if(c1.isPresent()){
            c =  c1.get();
        }

        c.setLicensePlate(carDto.getLicensePlate());
        c.setYear(carDto.getYear());
        c.setColor(carDto.getColor());
        c.setState(carDto.isState());
        Driver driver = repositoryDriver.findById(carDto.getDriverId()).get();
        c.setDriver(driver);
        Model model = repositoryModel.findById(carDto.getModelId()).get();
        c.setModel(model);

        return repositoryCar.save(c);


    }

    @Override
    public void delete(String id) {
        repositoryCar.deleteById(id);
    }
}
