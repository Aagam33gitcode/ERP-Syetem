package com.example.ERP.System.auth.Controllrt;

import com.example.ERP.System.auth.Entities.RoleEntity;
import com.example.ERP.System.auth.Repository.RoleRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleSeeder implements CommandLineRunner {

    @Autowired
    private RoleRespository roleRepository;

    @Override
    public void run(String... args) {
        if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
            roleRepository.save(new RoleEntity("ROLE_ADMIN"));
        }
        if (roleRepository.findByName("ROLE_EMPLOYEE").isEmpty()) {
            roleRepository.save(new RoleEntity("ROLE_EMPLOYEE"));
        }
    }
}