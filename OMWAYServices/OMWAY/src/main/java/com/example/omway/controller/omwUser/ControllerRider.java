package com.example.omway.controller.omwUser;

import com.example.omway.dto.omwUser.LoginResponseDto;
import com.example.omway.dto.omwUser.RiderDto;
import com.example.omway.model.omwUser.Rider;
import com.example.omway.service.omwUser.IServiceRider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rider")
@CrossOrigin("*")
public class ControllerRider {

    @Autowired
    private IServiceRider serviceRider;


    @GetMapping("/all")
    public List<Rider> getAll(){
        return serviceRider.getAll();

    }
    @GetMapping("/{cif}")
    public Rider findRiderByCif(@PathVariable String cif){return serviceRider.findRiderByCif(cif);}

    @PostMapping("/login")
    public LoginResponseDto getRiderByCif(@Param("cif") String cif, @Param("password") String password){
        return serviceRider.getRiderByCif(cif,password);
    }

    @PostMapping(value="/save",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Rider saveRider(@RequestBody RiderDto riderDto){
        return serviceRider.save(riderDto);
    }

    @PutMapping( "/update")
    public Rider updateRider(@RequestBody RiderDto riderDto) throws Exception{
        if(riderDto.getRiderCif()==null){
            throw new Exception("Please type the cif value");
        }
        return serviceRider.save(riderDto);
    }

    @DeleteMapping(value = "/delete/{cif}")
    public void deleteRider(@PathVariable String cif){
        serviceRider.deleteByString(cif);
    }

}


