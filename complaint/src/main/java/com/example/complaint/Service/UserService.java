package com.example.complaint.Service;

import com.example.complaint.DTO.Response.UserResponse;
import com.example.complaint.Entity.User;
import com.example.complaint.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User createUser(User user)
    {
        return userRepository.save(user);
    }

    public List<UserResponse> getUsers()
    {
        List<User> users =  userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        for(User k : users)
        {
            String userName = k.getName();
            String userEmail = k.getEmail();
            UserResponse userResponse = new UserResponse();
            userResponse.setUserName(userName);
            userResponse.setUserEmail(userEmail);
            userResponses.add(userResponse);
        }
        return userResponses;
    }

    public UserResponse getUsersById(Long id)
    {
        User user =  userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        UserResponse userResponse = new UserResponse();
        userResponse.setUserEmail(user.getEmail());
        userResponse.setUserName(user.getName());
        return userResponse;
    }

    public void deleteUserById(Long id)
    {
        User user =  userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}
