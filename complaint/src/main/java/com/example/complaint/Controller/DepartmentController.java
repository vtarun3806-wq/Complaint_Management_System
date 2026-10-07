package com.example.complaint.Controller;


import com.example.complaint.DTO.Response.DepartmentResponse;
import com.example.complaint.Entity.Department;
import com.example.complaint.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/create")
    ResponseEntity<Department> createDepartment(@RequestBody Department department)
    {
        return new ResponseEntity<>(departmentService.createDepartment(department), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    ResponseEntity<List<DepartmentResponse>> getAllDepartments()
    {
        return new ResponseEntity<>(departmentService.getAllDepartments(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable Long id)
    {
        return new ResponseEntity<>(departmentService.getDepartmentById(id),HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    ResponseEntity<String> deleteDepartmentById(@PathVariable Long id)
    {
        departmentService.deleteDepartmentById(id);
        return new ResponseEntity<>("Department deleted successfully",HttpStatus.OK);
    }
}
