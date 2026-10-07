package com.example.complaint.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long deptId;

    @Column(name = "department_name")
    private String departmentName;

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private List<Complaint> complaints;
}
