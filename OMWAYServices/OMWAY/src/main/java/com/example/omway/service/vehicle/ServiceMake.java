package com.example.omway.service.vehicle;

import com.example.omway.dto.vehicle.MakeDto;
import com.example.omway.model.vehicle.Make;
import com.example.omway.repository.vehicle.IRepositoryMake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ServiceMake implements IServiceMake {

    @Autowired
    private IRepositoryMake repositoryMake;

    @Override
    public List<Make> getAll() {
        return repositoryMake.findAll();
    }

    @Override
    public Make save(MakeDto makeDto) {
        Optional <Make> m1 = repositoryMake.findById(makeDto.getId());
        Make m = new Make();
        if(m1.isPresent()){
            m = m1.get();
        }
        m.setName(makeDto.getName());
        return repositoryMake.save(m);


    }

    @Override
    public void deleteById(Integer id) {
        repositoryMake.deleteById(id);
    }
}
