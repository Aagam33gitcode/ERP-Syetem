package com.example.ERP.System.Finance.JOURNAL;

import com.example.ERP.System.HR.Department.Model.departmentEntity;
import com.example.ERP.System.HR.Employee.Model.employeeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name = "JournalTable")
public class journalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long journalId;
    private BigDecimal debitAmount;
    private BigDecimal creditAmount;
    private String description;
    private LocalDateTime dataentryTime;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private employeeEntity employee;

    @ManyToOne
    @JoinColumn(name = "department_id")
private departmentEntity department;
journalEntity(){}
    public Long getJournalId() {
        return journalId;
    }

    public void setJournalId(Long journalId) {
        this.journalId = journalId;
    }

    public BigDecimal getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(BigDecimal debitAmount) {
        this.debitAmount = debitAmount;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDataentryTime() {
        return dataentryTime;
    }

    public void setDataentryTime(LocalDateTime dataentryTime) {
        this.dataentryTime = dataentryTime;
    }

    public employeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(employeeEntity employee) {
        this.employee = employee;
    }

    public departmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(departmentEntity department) {
        this.department = department;
    }

    public journalEntity(Long journalId, BigDecimal debitAmount, BigDecimal creditAmount, String description, LocalDateTime dataentryTime, employeeEntity employee, departmentEntity department) {
        this.journalId = journalId;
        this.debitAmount = debitAmount;
        this.creditAmount = creditAmount;
        this.description = description;
        this.dataentryTime = dataentryTime;
        this.employee = employee;
        this.department = department;
    }
}
