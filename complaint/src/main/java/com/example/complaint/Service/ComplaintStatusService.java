package com.example.complaint.Service;

import com.example.complaint.DTO.Request.ComplaintStatusRequest;
import com.example.complaint.Entity.Complaint;
import com.example.complaint.Entity.ComplaintStatus;
import com.example.complaint.Entity.StatusType;
import com.example.complaint.Repository.ComplaintRepository;
import com.example.complaint.Repository.ComplaintStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComplaintStatusService {
    @Autowired
    private ComplaintStatusRepository complaintStatusRepository;
    @Autowired
    private ComplaintRepository complaintRepository;

    public ComplaintStatus createComplaintStatus(ComplaintStatusRequest complaintStatusRequest)
    {
        String complaintStatusType = complaintStatusRequest.getStatusType();
        Long complaintId = complaintStatusRequest.getComplaintId();
        Complaint complaint = complaintRepository.findById(complaintId).orElseThrow(() -> new RuntimeException("Complaint not found"));
        ComplaintStatus complaintStatus = new ComplaintStatus();
        complaintStatus.setStatusType(StatusType.valueOf(complaintStatusType));
        complaintStatus.setComplaint(complaint);
        complaintStatus.setChangedAt(LocalDateTime.now());
        return complaintStatusRepository.save(complaintStatus);
    }

    public List<ComplaintStatus> getAllComplaintStatus()
    {
        return complaintStatusRepository.findAll();
    }

    public ComplaintStatus getComplaintStatusById(Long id)
    {
        return complaintStatusRepository.findById(id).orElseThrow(() -> new RuntimeException("ComplaintStatus not found"));
    }



    public void deleteComplaintStatusById(Long id)
    {
        ComplaintStatus complaintStatus = complaintStatusRepository.findById(id).orElseThrow(() -> new RuntimeException("ComplaintStatus not found"));
        complaintStatusRepository.delete(complaintStatus);
    }
}
