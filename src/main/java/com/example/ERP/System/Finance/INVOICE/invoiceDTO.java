package com.example.ERP.System.Finance.INVOICE;

import java.math.BigDecimal;
import java.time.LocalDate;

public class invoiceDTO {
    private Long id;
    private Long employeeId;
    private String customerName;
    private BigDecimal totalAmount;
    private String status;
    private LocalDate dueDate;
    private LocalDate issuedDate;
    private Long departmentId;
invoiceDTO(){}
    public invoiceDTO(Long id, String customerName, BigDecimal totalAmount, String status, LocalDate dueDate,Long employeeId, LocalDate issuedDate, Long departmentId) {
        this.id = id;
        this.customerName = customerName;
        this.employeeId=employeeId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.dueDate = dueDate;
        this.issuedDate = issuedDate;
        this.departmentId = departmentId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
