package com.example.omway.service.omwUser;

import com.example.omway.dto.omwUser.LoginResponseDto;
import com.example.omway.dto.omwUser.RiderDto;
import com.example.omway.model.omwUser.Rider;
import com.example.omway.repository.omwUser.IRepositoryRider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceRider implements IServiceRider {


    @Autowired
    private IRepositoryRider repositoryRider;
    @Override
    public List<Rider> getAll() {
        return repositoryRider.findAll();
    }

    @Override
    public Rider findRiderByCif(String cif) {
        Optional<Rider> r1 = repositoryRider.findById(cif);
        Rider r = new Rider();
        if (r1.isPresent()) {
            r = r1.get();
        }
        return r;
    }

    @Override
    public Rider save(RiderDto riderDto) {

        Optional <Rider> r1 = repositoryRider.findById(riderDto.getRiderCif());
        Rider r = new Rider();
        if(r1.isPresent()) {
            r = r1.get();
        }
        r.setCif(riderDto.getRiderCif());
        r.setName(riderDto.getName());
        r.setPassword(riderDto.getPassword());
        r.setPhone(riderDto.getPhone());
        r.setEmail(riderDto.getEmail());
        r.setState(riderDto.isState());

        return repositoryRider.save(r);
    }



    @Override
    public void deleteByString(String cif) {
        repositoryRider.deleteById(cif);
    }

    @Override
    public LoginResponseDto getRiderByCif(String cif, String password) {
        Rider r = repositoryRider.getRiderByCif(cif,password);
        LoginResponseDto lr = new LoginResponseDto(false,"Not Connected");
        if(r !=null){
            lr = new LoginResponseDto(true,"User Connected");
        }
        return lr;


    }
}
