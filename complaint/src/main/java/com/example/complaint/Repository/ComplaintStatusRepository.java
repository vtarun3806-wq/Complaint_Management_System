package com.example.complaint.Repository;

import com.example.complaint.Entity.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintStatusRepository extends JpaRepository<ComplaintStatus,Long> {
}
