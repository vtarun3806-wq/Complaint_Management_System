package com.example.complaint.DTO.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequest {
    private String employeeName;
    private Long departmentId;
}
