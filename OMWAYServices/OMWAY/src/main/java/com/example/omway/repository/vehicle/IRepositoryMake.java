package com.example.omway.repository.vehicle;



import com.example.omway.model.vehicle.Make;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryMake extends JpaRepository<Make, Integer> {



}
