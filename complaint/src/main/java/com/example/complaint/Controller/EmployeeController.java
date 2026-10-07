package com.example.complaint.Controller;

import com.example.complaint.DTO.Request.EmployeeRequest;
import com.example.complaint.DTO.Response.EmployeeComplaintsResponse;
import com.example.complaint.DTO.Response.EmployeeResponse;
import com.example.complaint.Entity.Employee;
import com.example.complaint.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    ResponseEntity<Employee> createEmployee(@RequestBody EmployeeRequest employeeRequest)
    {
        return new ResponseEntity<>(employeeService.createEmployee(employeeRequest.getEmployeeName(),employeeRequest.getDepartmentId()), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    ResponseEntity<List<EmployeeResponse>> getAllEmployees()
    {
        return new ResponseEntity<>(employeeService.getAllEmployees(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id)
    {
        return new ResponseEntity<>(employeeService.getEmployeeById(id),HttpStatus.OK);
    }

    @GetMapping("/{id}/complaints")
    ResponseEntity<EmployeeComplaintsResponse> getAllComplaints(@PathVariable Long id)
    {
        return new ResponseEntity<>(employeeService.getAllComplaints(id),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteEmployeeById(@PathVariable Long id)
    {
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>("Deleted Employee successfully",HttpStatus.OK);
    }

}
