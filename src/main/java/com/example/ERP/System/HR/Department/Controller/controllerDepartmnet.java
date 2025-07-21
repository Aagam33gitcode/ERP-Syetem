package com.example.ERP.System.HR.Department.Controller;

import com.example.ERP.System.HR.Department.Model.departmentDto;
import com.example.ERP.System.HR.Department.Service.depService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.util.List;

@RestController
@RequestMapping("/department")
public class controllerDepartmnet {
private final depService service;
controllerDepartmnet(depService service){
    this.service=service;
}



@GetMapping("/getAll")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")

    public ResponseEntity<List<departmentDto>> getDepartments(){
    List<departmentDto> department=service.getAlldepartment();
    return new ResponseEntity<>(department, HttpStatus.OK);
}

@GetMapping("/{departmentId}")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")

    public ResponseEntity<departmentDto> getByID(@PathVariable(name="departmentId") Long departmentId){
    departmentDto department=service.getDepartmentById(departmentId);
    return new ResponseEntity<>(department,HttpStatus.FOUND);
    }

    @PostMapping("/department")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<departmentDto> adddepartment(@RequestBody departmentDto dto){
    departmentDto department=service.addNewDepartment(dto);
    return new ResponseEntity<>(department,HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{departmentId}")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<String> deleteDepartment(@PathVariable(name = "departmentId") Long departmentId){
    String department=service.deleteDepartment(departmentId);
    return new ResponseEntity<>(department,HttpStatus.OK);
    }
}
