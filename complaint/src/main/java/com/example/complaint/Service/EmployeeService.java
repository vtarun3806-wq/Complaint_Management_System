package com.example.complaint.Service;

import com.example.complaint.DTO.Response.ComplaintEmployeesResponse;
import com.example.complaint.DTO.Response.EmployeeComplaintsResponse;
import com.example.complaint.DTO.Response.EmployeeResponse;
import com.example.complaint.DTO.Response.UserResponse;
import com.example.complaint.Entity.Complaint;
import com.example.complaint.Entity.Department;
import com.example.complaint.Entity.Employee;
import com.example.complaint.Entity.User;
import com.example.complaint.Repository.DepartmentRepository;
import com.example.complaint.Repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    public Employee createEmployee(String employeeName, Long departmentId)
    {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new RuntimeException("Department not found"));
        Employee employee = new Employee();
        employee.setEmployeeName(employeeName);
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    public List<EmployeeResponse> getAllEmployees()
    {
        List<Employee> employees =  employeeRepository.findAll();
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        for(Employee k : employees)
        {
            String employeeName = k.getEmployeeName();
            String departmentName = k.getDepartment().getDepartmentName();
            EmployeeResponse employeeResponse = new EmployeeResponse();
            employeeResponse.setEmployeeName(employeeName);
            employeeResponse.setDepartmentName(departmentName);
            employeeResponses.add(employeeResponse);
        }
        return employeeResponses;
    }

    public EmployeeResponse getEmployeeById(Long id)
    {
        Employee employee =  employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setEmployeeName(employee.getEmployeeName());
        employeeResponse.setDepartmentName(employee.getDepartment().getDepartmentName());
        return employeeResponse;
    }

    @Transactional
    public EmployeeComplaintsResponse getAllComplaints(Long id)
    {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        List<Complaint> complaints = employee.getComplaints();
        List<String> complaintDescriptions = new ArrayList<>();
        for(Complaint k : complaints)
        {
            complaintDescriptions.add(k.getComplaintDescription());
        }
        EmployeeComplaintsResponse employeeComplaintsResponse = new EmployeeComplaintsResponse();
        employeeComplaintsResponse.setComplaintNames(complaintDescriptions);
        return employeeComplaintsResponse;
    }

    public void deleteEmployeeById(Long id)
    {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        employeeRepository.delete(employee);
    }
}
