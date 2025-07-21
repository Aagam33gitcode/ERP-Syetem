package com.example.ERP.System.HR.Employee.Repository;

import com.example.ERP.System.HR.Employee.Model.employeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface employeeRepository extends JpaRepository<employeeEntity,Long> {
//    List<employeeEntity> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrJobTitleContainingIgnoreCaseOrDepartment_NameContainingIgnoreCase(
//            String firstName,
//            String lastName,
//            String jobTitle,
//            String departmentName
//    );
}
