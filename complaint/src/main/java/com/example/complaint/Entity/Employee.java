package com.example.complaint.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "employee_name")
    private String employeeName;


    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @JsonIgnore
    @ManyToMany(mappedBy = "employees")
    private List<Complaint> complaints;

}
