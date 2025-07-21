package com.example.ERP.System.auth.Repository;

import com.example.ERP.System.auth.Entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRespository  extends JpaRepository<RoleEntity,Long> {

    Optional<RoleEntity> findByName(String name);

}
