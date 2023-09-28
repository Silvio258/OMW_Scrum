package com.example.omway.controller.vehicle;

import com.example.omway.dto.vehicle.ModelDto;
import com.example.omway.model.vehicle.Make;
import com.example.omway.model.vehicle.Model;
import com.example.omway.service.vehicle.IServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/model")
public class ControllerModel {
    @Autowired
    private IServiceModel serviceModel;
    @GetMapping("/all")
    public List<Model> getAll() {
        return serviceModel.getAll();
    }

    @GetMapping("modelsByMake/{makeId}")
    public List<Model> getModelsByMake(@PathVariable Integer makeId){return serviceModel.getModelsByMake(makeId);}
    @PostMapping("/save")
    public Model save(@RequestBody ModelDto modelDto){
        return serviceModel.save(modelDto);
    }

    @PutMapping(value = "/update")
    public Model updateModel(@RequestBody ModelDto modelDto) throws Exception{
        if(modelDto.getId()==null){
            throw new Exception("Please type the Id value");
        }
        return serviceModel.save(modelDto);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer modelId){
            serviceModel.delete(modelId);
    }

}