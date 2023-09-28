package com.example.omway.repository.settingsOMWay;

import com.example.omway.model.settingsOMWay.ConfigFare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.LongSummaryStatistics;

@Repository
public interface IRepositoryConfigFare extends JpaRepository <ConfigFare,Integer>{
      //  List<ConfigFare> findAllByStatus(boolean condition);
        ConfigFare findByName(String name);
}
