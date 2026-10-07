package com.example.complaint.Controller;



import com.example.complaint.DTO.Request.ComplaintStatusRequest;
import com.example.complaint.Entity.ComplaintStatus;
import com.example.complaint.Service.ComplaintStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaintstatus")
public class ComplaintStatusController {
    @Autowired
    private ComplaintStatusService complaintStatusService;

    @PostMapping("/create")
    ResponseEntity<ComplaintStatus> createComplaintStatus(@RequestBody ComplaintStatusRequest complaintStatusRequest)
    {
        return new ResponseEntity<>(complaintStatusService.createComplaintStatus(complaintStatusRequest), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    ResponseEntity<List<ComplaintStatus>> getAllComplaintStatus()
    {
        return new ResponseEntity<>(complaintStatusService.getAllComplaintStatus(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<ComplaintStatus> getComplaintStatusById(@PathVariable Long id)
    {
        return new ResponseEntity<>(complaintStatusService.getComplaintStatusById(id),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteComplaintStatusById(@PathVariable Long id)
    {
        complaintStatusService.deleteComplaintStatusById(id);
        return new ResponseEntity<>("ComplaintStatus Deleted Successfully",HttpStatus.OK);
    }


}
