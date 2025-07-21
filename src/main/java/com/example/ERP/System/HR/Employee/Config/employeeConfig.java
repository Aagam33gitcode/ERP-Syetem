package com.example.ERP.System.HR.Employee.Config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class employeeConfig {
    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
