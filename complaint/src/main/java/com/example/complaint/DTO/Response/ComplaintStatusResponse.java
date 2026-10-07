package com.example.complaint.DTO.Response;

import com.example.complaint.Entity.ComplaintStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ComplaintStatusResponse {
    private String complaintDescription;
    private List<ComplaintStatus> complaintStatusList;
}
