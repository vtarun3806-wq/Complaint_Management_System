package com.example.complaint.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_name")
    private String name;
    @Column(name = "user_email")
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Complaint> complaints;

}
