package com.example.omway.repository.vehicle;

import com.example.omway.model.vehicle.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IRepositoryModel extends JpaRepository<Model, Integer> {
    @Query("select e from Model e where e.make.id = :makeId")
    List<Model> getModelsByMake(@Param("makeId")Integer makeId);
}