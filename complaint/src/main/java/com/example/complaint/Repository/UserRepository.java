package com.example.complaint.Repository;

import com.example.complaint.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
