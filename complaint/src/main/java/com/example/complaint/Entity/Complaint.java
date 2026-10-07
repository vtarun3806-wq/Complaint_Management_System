package com.example.complaint.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "complaint")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complaint_id")
    private Long complaintId;

    @Column(name = "complaint_description")
    private String complaintDescription;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "complaint")
    @JsonIgnore
    private List<ComplaintStatus> statusRecords;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "employee_complaint", joinColumns = @JoinColumn(name = "complaint_id"),inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employees;
}
