package com.example.complaint.DTO.Request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ComplaintRequest {
    private String complaintDescription;
    private Long userId;
    private Long departmentId;
}
