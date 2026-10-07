package com.example.complaint.DTO.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplaintStatusRequest {
    private String statusType;
    private Long complaintId;
}
