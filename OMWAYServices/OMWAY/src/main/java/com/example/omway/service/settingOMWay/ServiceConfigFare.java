package com.example.omway.service.settingOMWay;

import com.example.omway.model.omwUser.Driver;
import com.example.omway.model.settingsOMWay.ConfigFare;
import com.example.omway.repository.settingsOMWay.IRepositoryConfigFare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ServiceConfigFare implements IServiceConfigFare {

    @Autowired
    private IRepositoryConfigFare repositoryConfigFare;

    @Override
    public List<ConfigFare> getConfigFare() {

        return repositoryConfigFare.findAll();
    }

    @Override
    public ConfigFare save(ConfigFare configFare) {

        Optional <ConfigFare> c1 =repositoryConfigFare.findById(configFare.getId());
        ConfigFare c = new ConfigFare();
        if(c1.isPresent()){
            c = c1.get();
        }

        c.setName(configFare.getName());
        c.setFare(configFare.getFare());
        c.setState(configFare.isState());
        return repositoryConfigFare.save(configFare);

    }

    @Override
    public void deleteConfigFare(Integer id) {

        repositoryConfigFare.deleteById(id);

    }

}
