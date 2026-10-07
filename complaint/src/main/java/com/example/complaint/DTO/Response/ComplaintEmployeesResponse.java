package com.example.complaint.DTO.Response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ComplaintEmployeesResponse {
    private List<String> employeeNames;
}
