package com.example.ERP.System.HR.Department.Model;

import jakarta.persistence.*;


@Entity
@Table(name = "DepartmentTable")
public class departmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;
    @Column(nullable = false, unique = true)
    private String departmentName;

    departmentEntity(){}
    public departmentEntity( String departmentName,Long departmentId) {
        this.departmentName = departmentName;
        this.departmentId=departmentId;
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
