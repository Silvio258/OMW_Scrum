package com.example.omway.service.omwUser;

import com.example.omway.dto.omwUser.LoginResponseDto;
import com.example.omway.dto.omwUser.RiderDto;
import com.example.omway.model.omwUser.OMWayUser;
import com.example.omway.model.omwUser.Rider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IServiceRider {
    public List<Rider> getAll();
    Rider findRiderByCif(String cif);

    public Rider save(RiderDto riderDto);

    public void deleteByString(String cif);
    LoginResponseDto getRiderByCif(String cif,String password);
}
