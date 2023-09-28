package com.example.omway.service.vehicle;

import com.example.omway.dto.vehicle.ModelDto;
import com.example.omway.model.vehicle.Model;
import com.example.omway.repository.vehicle.IRepositoryMake;
import com.example.omway.repository.vehicle.IRepositoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceModel implements IServiceModel {
    @Autowired
    private IRepositoryModel repositoryModel;
    @Autowired
    private IRepositoryMake makeRepository;

    @Override
    public List<Model> getAll() {
        return repositoryModel.findAll();
    }

    @Override
    public List<Model> getModelsByMake(Integer makeId) {
        return repositoryModel.getModelsByMake(makeId);
    }

    @Override
    public Model save(@RequestBody ModelDto modelDto) {

        Optional <Model> m1 = repositoryModel.findById(modelDto.getId());
        Model m = new Model();
        if(m1.isPresent()){
            m = m1.get();
        }

        m.setId(modelDto.getId());
        m.setName(modelDto.getName());
        m.setMake(
            makeRepository.findById(modelDto.getMakeId()).get()
        );
        return repositoryModel.save(m);

    }

    @Override
    public void delete(Integer modelId) {
        repositoryModel.deleteById(modelId);
    }
}