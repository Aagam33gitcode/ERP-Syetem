package com.example.ERP.System.HR.Employee.Model;
import java.time.LocalDate;

public class employeeDto {
    private long empId;
    private String empName;
    private String email;
    private LocalDate hireDate;
    private String jobTitle;
    private String department;


    //for image
    private String empImageName;
    private  String empImageType;
    private byte[] empImageData;
    employeeDto() {}

    public employeeDto(long empId, String empName, String email, LocalDate hireDate, String jobTitle, String department, String empImageName, String empImageType, byte[] empImageData) {
        this.empId = empId;
        this.empName = empName;
        this.email = email;
        this.hireDate = hireDate;
        this.jobTitle = jobTitle;
        this.department = department;
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

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

