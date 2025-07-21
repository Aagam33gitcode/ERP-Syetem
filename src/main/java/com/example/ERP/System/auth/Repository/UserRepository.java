package com.example.ERP.System.auth.Repository;

import com.example.ERP.System.auth.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity,Long> {


    Optional<UserEntity> findByUsername(String username);
}
