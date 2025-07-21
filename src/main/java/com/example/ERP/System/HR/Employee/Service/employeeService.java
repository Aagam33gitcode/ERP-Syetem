package com.example.ERP.System.HR.Employee.Service;

import com.example.ERP.System.HR.Department.Model.departmentEntity;
import com.example.ERP.System.HR.Department.Repository.departmentRepo;
import com.example.ERP.System.HR.Employee.Model.employeeDto;
import com.example.ERP.System.HR.Employee.Model.employeeEntity;
import com.example.ERP.System.HR.Employee.Repository.employeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class employeeService {
    private final employeeRepository repo;
    private final departmentRepo dpRepo;
    private final ModelMapper mapper;

    employeeService(employeeRepository repo, ModelMapper mapper,departmentRepo dpRepo) {
        this.repo = repo;
        this.mapper = mapper;
        this.dpRepo=dpRepo;
    }

    public List<employeeDto> getAllEmployee() {
        List<employeeEntity> employee = repo.findAll();
        return employee
                .stream()
                .map(dto -> mapper.map(dto, employeeDto.class))
                .collect(Collectors.toList());
    }

    public employeeDto getEmployeeById(Long empId) {
        employeeEntity employee = repo.findById(empId).orElseThrow(() -> new NoSuchElementException(empId + " employee is not exist!"));
        return mapper.map(employee, employeeDto.class);
    }

    public employeeDto createNewEmployee(employeeDto employee, MultipartFile imagefile) {
        employeeEntity employee1=mapper.map(employee,employeeEntity.class);
        departmentEntity department= dpRepo.findByDepartmentName(employee.getDepartment()).orElseThrow(()->new RuntimeException(employee.getDepartment() +"department not found"));
employee1.setDepartment(department);
        // for  image
        if(imagefile!=null&& !imagefile.isEmpty()) {
            try {
                employee1.setEmpImageName(imagefile.getOriginalFilename());
                employee1.setEmpImageType(imagefile.getContentType());
                employee1.setEmpImageData(imagefile.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("not valid " + e);
            }
        }
        //to save in database
        employeeEntity saved = repo.save(employee1);
        //convert into DTO
            return mapper.map(saved,employeeDto.class);

    }

  public String deleteemployee(Long empId) {
      try{
          if(repo.findById(empId).isPresent()) {
              repo.deleteById(empId);

          }return "deleted";
      }catch (Exception e){
          throw  new RuntimeException("not found "+ empId);
  }}
}