package com.example.omway.service.vehicle;
import com.example.omway.dto.vehicle.ModelDto;
import com.example.omway.model.omwUser.Rider;
import com.example.omway.model.vehicle.Model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IServiceModel {

    public List<Model> getAll();


    List<Model> getModelsByMake(Integer makeId);

    public Model save(ModelDto modelDto);

    public void delete(Integer modelId);

}