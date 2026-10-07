package com.example.complaint.Service;

import com.example.complaint.DTO.Request.ComplaintRequest;
import com.example.complaint.DTO.Response.ComplaintEmployeesResponse;
import com.example.complaint.DTO.Response.ComplaintResponse;
import com.example.complaint.DTO.Response.ComplaintStatusResponse;
import com.example.complaint.Entity.*;
import com.example.complaint.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComplaintService {
    @Autowired
    private ComplaintRepository complaintRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ComplaintStatusRepository complaintStatusRepository;


    public Complaint createComplaint(ComplaintRequest complaintRequest)
    {
        String complaintDescription = complaintRequest.getComplaintDescription();
        Long userId = complaintRequest.getUserId();
        Long departmentId = complaintRequest.getDepartmentId();

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new RuntimeException("department not found"));
        Complaint complaint  = new Complaint();
        complaint.setComplaintDescription(complaintDescription);
        complaint.setUser(user);
        complaint.setDepartment(department);
        return complaintRepository.save(complaint);
    }

    @Transactional
    public Complaint assignEmployees(Long complaintId, Long employeeId)
    {
        Complaint complaint = complaintRepository.findById(complaintId).orElseThrow(() -> new RuntimeException("Complaint not found"));
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));

        if(complaint.getDepartment().getDeptId().equals(employee.getDepartment().getDeptId()))
        {
            complaint.getEmployees().add(employee);
            return complaintRepository.save(complaint);
        }

        else {
            throw new RuntimeException("Employee does not belong to the complaint's department");
        }
    }

    public List<ComplaintResponse> getAllComplaints()
    {
        List<Complaint> complaints =  complaintRepository.findAll();
        List<ComplaintResponse> complaintResponses = new ArrayList<>();
        for(Complaint k : complaints)
        {
            Department department = departmentRepository.findById(k.getDepartment().getDeptId()).orElseThrow(() -> new RuntimeException("Department not found"));
            ComplaintResponse complaintResponse = new ComplaintResponse();
            complaintResponse.setComplaintDescription(k.getComplaintDescription());
            complaintResponse.setDepartmentName(department.getDepartmentName());
            complaintResponses.add(complaintResponse);
        }
        return complaintResponses;
    }

    @Transactional
    public ComplaintEmployeesResponse getAllEmployees(Long id)
    {
        Complaint complaint = complaintRepository.findById(id).orElseThrow(() -> new RuntimeException("Complaint not found"));
        List<Employee> employees = complaint.getEmployees();
        List<String> employeeNames = new ArrayList<>();
        for(Employee k : employees)
        {
            employeeNames.add(k.getEmployeeName());
        }
        ComplaintEmployeesResponse complaintEmployeesResponse = new ComplaintEmployeesResponse();
        complaintEmployeesResponse.setEmployeeNames(employeeNames);
        return complaintEmployeesResponse;
    }

    public ComplaintResponse getComplaintById(Long id)
    {
        Complaint complaint =  complaintRepository.findById(id).orElseThrow(() -> new RuntimeException("Complaint not found"));
        Department department = departmentRepository.findById(complaint.getDepartment().getDeptId()).orElseThrow(() -> new RuntimeException("Department not found"));
        ComplaintResponse complaintResponse = new ComplaintResponse();
        complaintResponse.setComplaintDescription(complaint.getComplaintDescription());
        complaintResponse.setDepartmentName(department.getDepartmentName());
        return complaintResponse;
    }

    public void deleteComplaintById(Long id)
    {
        Complaint complaint = complaintRepository.findById(id).orElseThrow(() -> new RuntimeException("Complaint not found"));
        complaintRepository.delete(complaint);
    }

    public ComplaintStatusResponse getComplaintStatus(Long id)
    {
        Complaint complaint = complaintRepository.findById(id).orElseThrow(() -> new RuntimeException("Complaint not found"));
        String complaintDescription = complaint.getComplaintDescription();
        Long complaintId = complaint.getComplaintId();
        List<ComplaintStatus> complaintStatus = new ArrayList<>();
        List<ComplaintStatus> complaintStatusList = complaintStatusRepository.findAll();
        for(ComplaintStatus l : complaintStatusList)
        {
            Long complaintIds  = l.getComplaint().getComplaintId();
            if(complaintIds.equals(complaintId)) {
                complaintStatus.add(l);
            }
        }
        ComplaintStatusResponse complaintStatusResponse = new ComplaintStatusResponse();
        complaintStatusResponse.setComplaintDescription(complaintDescription);
        complaintStatusResponse.setComplaintStatusList(complaintStatus);
        return complaintStatusResponse;
    }
}
