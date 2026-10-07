package com.example.complaint.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "complaint_status")
public class ComplaintStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Long statusId;

    @Column(name = "status_type")
    @Enumerated(EnumType.STRING)
    private StatusType statusType;

    @ManyToOne
    @JoinColumn(name = "complaint_id")
    private Complaint complaint;

    private LocalDateTime changedAt;

}
