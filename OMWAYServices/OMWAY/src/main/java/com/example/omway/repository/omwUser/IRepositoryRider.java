package com.example.omway.repository.omwUser;

import com.example.omway.dto.omwUser.LoginResponseDto;
import com.example.omway.model.omwUser.Driver;
import com.example.omway.model.omwUser.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryRider extends JpaRepository<Rider,String> {
    @Query("select e from Rider e where e.cif = :cif and e.password =:password")
    public Rider getRiderByCif(@Param("cif") String cif,@Param("password") String password);

}
