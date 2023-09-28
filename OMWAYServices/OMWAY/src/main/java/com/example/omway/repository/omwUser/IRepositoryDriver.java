package com.example.omway.repository.omwUser;

import com.example.omway.model.omwUser.Driver;
import com.example.omway.model.omwUser.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryDriver extends JpaRepository<Driver,String> {
    @Query("select e from Driver e where e.cif = :cif and e.password =:password")
    public Driver getDriverByCif(@Param("cif") String cif,@Param("password") String password);
}
