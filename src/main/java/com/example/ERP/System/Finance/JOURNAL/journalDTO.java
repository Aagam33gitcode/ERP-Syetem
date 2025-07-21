package com.example.ERP.System.Finance.JOURNAL;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class journalDTO {
    private Long journalId;
    private String description;
    private BigDecimal debit;
    private BigDecimal credit;
    private LocalDateTime entryTimestamp;
    private Long employeeId;
    private String employeeName;
    private Long departmentId;
    private String departmentName;

//    journalDTO(){}

//    public journalDTO(Long journalId, String description, BigDecimal debit, BigDecimal credit, LocalDateTime entryTimestamp, Long employeeId, String employeeName, Long departmentId, String departmentName) {
//        this.journalId = journalId;
//        this.description = description;
//        this.debit = debit;
//        this.credit = credit;
//        this.entryTimestamp = entryTimestamp;
//        this.employeeId = employeeId;
//        this.employeeName = employeeName;
//        this.departmentId = departmentId;
//        this.departmentName = departmentName;
//    }

    public Long getJournalId() {
        return journalId;
    }

    public void setJournalId(Long journalId) {
        this.journalId = journalId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getDebit() {
        return debit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public LocalDateTime getEntryTimestamp() {
        return entryTimestamp;
    }

    public void setEntryTimestamp(LocalDateTime entryTimestamp) {
        this.entryTimestamp = entryTimestamp;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
