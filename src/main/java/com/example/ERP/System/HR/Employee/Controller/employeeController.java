package com.example.ERP.System.HR.Employee.Controller;

import com.example.ERP.System.HR.Employee.Model.employeeDto;
import com.example.ERP.System.HR.Employee.Service.employeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/")
public class employeeController {
    private final employeeService service;
    employeeController(employeeService service){
       this.service=service;
    }
    public String sayHello(){
        return "hello bolo";
    }
    @GetMapping("employees")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'EMPLOYEE')")

    public ResponseEntity<List<employeeDto>> getAllEmployee(){
        List<employeeDto> employee=service.getAllEmployee();
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }
    @GetMapping("employees/{empid}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'EMPLOYEE')")

    public ResponseEntity<employeeDto> getEmployeeById(@PathVariable(name = "empid") Long empId){
        employeeDto employee =service.getEmployeeById(empId);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }


    @PostMapping("employee/multiple/add")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")

    public ResponseEntity<employeeDto> createNewEmployee(@RequestBody employeeDto employee ,@RequestPart MultipartFile imagefile){
        employeeDto dto=service.createNewEmployee(employee,imagefile);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{empmId}")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<String> deleteemployee(@PathVariable Long empId){
        return new ResponseEntity<>(service.deleteemployee(empId),HttpStatus.OK);
    }
}
