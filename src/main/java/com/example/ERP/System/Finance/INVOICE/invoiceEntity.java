package com.example.ERP.System.Finance.INVOICE;

import com.example.ERP.System.HR.Department.Model.departmentEntity;
import com.example.ERP.System.HR.Employee.Model.employeeEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "INVOICE")

public class invoiceEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;
    @ManyToOne
    @JoinColumn(name = "employee_id")
private employeeEntity customer;
private BigDecimal totalAmount;
private String status;
private LocalDate dueDate;
private LocalDate issueDate;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private departmentEntity department;
    invoiceEntity(){}
    public invoiceEntity(Long invoiceId, employeeEntity customer, BigDecimal totalAmount, String status,
                   LocalDate dueDate, LocalDate issueDate, departmentEntity department) {
        this.invoiceId = invoiceId;
        this.customer = customer;
        this.totalAmount = totalAmount;
        this.status = status;
        this.dueDate = dueDate;
        this.issueDate = issueDate;
        this.department = department;
    }
    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public employeeEntity getcustomer() {
        return customer;
    }

    public void setcustomer(employeeEntity customer) {
        this.customer = customer;
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

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public departmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(departmentEntity department) {
        this.department = department;
    }

}
