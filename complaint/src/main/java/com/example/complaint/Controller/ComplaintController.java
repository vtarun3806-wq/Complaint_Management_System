package com.example.complaint.Controller;

import com.example.complaint.DTO.Request.ComplaintRequest;
import com.example.complaint.DTO.Response.ComplaintEmployeesResponse;
import com.example.complaint.DTO.Response.ComplaintResponse;
import com.example.complaint.DTO.Response.ComplaintStatusResponse;
import com.example.complaint.Entity.Complaint;
import com.example.complaint.Service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaint")
public class ComplaintController {
    @Autowired
    private ComplaintService complaintService;

    @PostMapping("/create")
    ResponseEntity<Complaint> createComplaint(@RequestBody ComplaintRequest complaintRequest)
    {
        return new ResponseEntity<>(complaintService.createComplaint(complaintRequest), HttpStatus.CREATED);
    }

    @PostMapping("/create/{complaintId}/employees")
    ResponseEntity<Complaint> assignEmployees(@PathVariable Long complaintId, @RequestParam Long employeeId)
    {
        return new ResponseEntity<>(complaintService.assignEmployees(complaintId,employeeId),HttpStatus.OK);
    }

    @GetMapping("/all")
    ResponseEntity<List<ComplaintResponse>> getAllComplaints()
    {
        return new ResponseEntity<>(complaintService.getAllComplaints(),HttpStatus.OK);
    }

    @GetMapping("/{id}/employees")
    ResponseEntity<ComplaintEmployeesResponse> getAllEmployees(@PathVariable Long id)
    {
        return new ResponseEntity<>(complaintService.getAllEmployees(id),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<ComplaintResponse> getComplaintById(@PathVariable Long id)
    {
        return new ResponseEntity<>(complaintService.getComplaintById(id),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteComplaintById(@PathVariable Long id)
    {
        complaintService.deleteComplaintById(id);
        return new ResponseEntity<>("Complaint deleted Successfully",HttpStatus.OK);
    }

    @GetMapping("/{id}status")
    ResponseEntity<ComplaintStatusResponse> getComplaintStatus(@PathVariable Long id)
    {
        return new ResponseEntity<>(complaintService.getComplaintStatus(id),HttpStatus.OK);
    }

}
