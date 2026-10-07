package com.example.complaint.Service;

import com.example.complaint.DTO.Response.DepartmentResponse;
import com.example.complaint.DTO.Response.UserResponse;
import com.example.complaint.Entity.Department;

import com.example.complaint.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public Department createDepartment(Department department)
    {
        return departmentRepository.save(department);
    }

    public List<DepartmentResponse> getAllDepartments()
    {
        List<Department> departments =  departmentRepository.findAll();
        List<DepartmentResponse> departmentResponses = new ArrayList<>();
        for(Department k : departments)
        {
            String departmemtName = k.getDepartmentName();

            DepartmentResponse departmentResponse = new DepartmentResponse();
            departmentResponse.setDepartmentName(departmemtName);

            departmentResponses.add(departmentResponse);
        }
        return departmentResponses;
    }

    public DepartmentResponse getDepartmentById(Long id)
    {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
        DepartmentResponse departmentResponse= new DepartmentResponse();

        departmentResponse.setDepartmentName(department.getDepartmentName());
        return departmentResponse;
    }

    public void deleteDepartmentById(Long id)
    {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
        departmentRepository.delete(department);
    }
}
