package com.example.ERP.System.HR.Employee.Model;
import com.example.ERP.System.HR.Department.Model.departmentEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


import java.time.LocalDate;

@Entity
@Table(name = "Employee")
public class employeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long empId;
    private String empName;
   
    @ManyToOne
    @JoinColumn(name="department_id")
    private departmentEntity department;
    private String jobTitle;
    @Email
    @NotBlank
    private String email;
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
    private String phoneNo;
    private LocalDate hireDate;
    private Double salary;

    //for image
    private String empImageName;
    private  String empImageType;
    @Lob
    private byte[] empImageData;
    employeeEntity(){}


    public employeeEntity(long empId, String empName, departmentEntity department, String jobTitle, String email, String phoneNo, LocalDate hireDate, Double salary, String empImageName, String empImageType, byte[] empImageData) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.jobTitle = jobTitle;
        this.email = email;
        this.phoneNo = phoneNo;
        this.hireDate = hireDate;
        this.salary = salary;
        this.empImageName = empImageName;
        this.empImageType = empImageType;
        this.empImageData = empImageData;
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public String getFirstName() {
        return empName;
    }

    public void setFirstName(String empName) {
        this.empName = empName;
    }
    
    public departmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(departmentEntity department) {
        this.department = department;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public @Email @NotBlank String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotBlank String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getEmpImageName() {
        return empImageName;
    }

    public void setEmpImageName(String empImageName) {
        this.empImageName = empImageName;
    }

    public String getEmpImageType() {
        return empImageType;
    }

    public void setEmpImageType(String empImageType) {
        this.empImageType = empImageType;
    }

    public byte[] getEmpImageData() {
        return empImageData;
    }

    public void setEmpImageData(byte[] empImageData) {
        this.empImageData = empImageData;
    }
}
