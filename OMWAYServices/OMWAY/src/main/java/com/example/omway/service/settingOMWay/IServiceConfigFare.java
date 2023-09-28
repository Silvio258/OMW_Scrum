package com.example.omway.service.settingOMWay;

import com.example.omway.model.settingsOMWay.ConfigFare;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IServiceConfigFare {
    public List<ConfigFare> getConfigFare();

    public ConfigFare save(ConfigFare configFare);

    public void deleteConfigFare(Integer id);
}
