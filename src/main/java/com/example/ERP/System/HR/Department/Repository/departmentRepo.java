package com.example.ERP.System.HR.Department.Repository;

import com.example.ERP.System.HR.Department.Model.departmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface departmentRepo extends JpaRepository<departmentEntity,Long> {

    Optional<departmentEntity> findByDepartmentName(String departmentName );


    Optional<departmentEntity> findByDepartmentNameContainingIgnoreCase(String departmentName);
}
