package com.example.omway.service.omwUser;

import com.example.omway.dto.omwUser.DriverDto;
import com.example.omway.dto.omwUser.LoginResponseDto;
import com.example.omway.model.omwUser.Driver;
import com.example.omway.model.trip.Ride;
import com.example.omway.repository.omwUser.IRepositoryDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceDriver implements IServiceDriver {
    @Autowired
    private IRepositoryDriver repositoryDriver;
    @Override
    public List<Driver> getAll() {
        return repositoryDriver.findAll();
    }

    @Override
    public Driver findDriverByCif(String cif) {
        Optional<Driver> d1 = repositoryDriver.findById(cif);
        Driver d = new Driver();
        if (d1.isPresent()) {
            d = d1.get();
        }
        return d;
    }

    @Override
    public Driver save(DriverDto driverDto) {
        Optional<Driver> d1 = repositoryDriver.findById(driverDto.getDriverCif());
        Driver d = new Driver();
        if (d1.isPresent()){
            d = d1.get();
        }

        d.setCif(driverDto.getDriverCif());
        d.setName(driverDto.getName());
        d.setPassword(driverDto.getPassword());
        d.setPhone(driverDto.getPhone());
        d.setEmail(driverDto.getEmail());
        d.setState(driverDto.isState());
        d.setDlExpirationDate(driverDto.getDlExpirationDate());

        List<Ride> driverRides = d.getDriverRides();
        int numberOfRides = 0;
        int sum = 0;
        if(driverRides != null){
            numberOfRides = driverRides.size();
            for (Ride ride: driverRides){
                sum+= ride.getRating();
            }
        }
        d.setNumberRides(numberOfRides);
        d.setSumRating(sum);

        return repositoryDriver.save(d);
    }


    @Override
    public void deleteById(String cif) {
        repositoryDriver.deleteById(cif);
    }

    @Override
    public LoginResponseDto getDriverByCif(String cif, String password) {
        Driver d = repositoryDriver.getDriverByCif(cif,password);
        LoginResponseDto lr = new LoginResponseDto(false,"Not Connected");
        if(d !=null){
            lr = new LoginResponseDto(true,"User Connected");
        }
        return lr;
    }
}
