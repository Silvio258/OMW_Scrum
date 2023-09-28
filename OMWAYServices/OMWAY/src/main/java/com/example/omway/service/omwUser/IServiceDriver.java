package com.example.omway.service.omwUser;

import com.example.omway.dto.omwUser.DriverDto;
import com.example.omway.dto.omwUser.LoginResponseDto;
import com.example.omway.model.omwUser.Driver;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IServiceDriver {
    public List<Driver> getAll();
    Driver findDriverByCif(String cif);

    public Driver save(DriverDto driverDto);


    public void deleteById(String cif);

    LoginResponseDto getDriverByCif(String cif, String password);
}
