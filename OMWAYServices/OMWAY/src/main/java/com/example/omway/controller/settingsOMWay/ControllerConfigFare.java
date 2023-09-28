package com.example.omway.controller.settingsOMWay;

import com.example.omway.model.settingsOMWay.ConfigFare;
import com.example.omway.service.settingOMWay.IServiceConfigFare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/configFare")
public class ControllerConfigFare {
    @Autowired
    private IServiceConfigFare serviceConfigFare;

    @GetMapping("/all")
    public List<ConfigFare> getAll(){
        return serviceConfigFare.getConfigFare();

    }

    @PostMapping("/save")
    public ConfigFare save(@RequestBody ConfigFare configFare){

        return serviceConfigFare.save(configFare);
    }

    @PutMapping(value = "/update")
    public ConfigFare updateConfigFare(@RequestBody ConfigFare configFare) throws SQLException {
        if(configFare.getId() == null){

            throw new SQLException("Please, send the Id Value");

        }
        return serviceConfigFare.save(configFare);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteConfigFare(@PathVariable Integer id){
        serviceConfigFare.deleteConfigFare(id);

    }




}
