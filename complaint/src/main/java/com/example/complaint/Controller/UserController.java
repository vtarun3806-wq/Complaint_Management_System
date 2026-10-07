package com.example.complaint.Controller;

import com.example.complaint.DTO.Response.UserResponse;
import com.example.complaint.Entity.User;
import com.example.complaint.Service.UserService;
import org.hibernate.internal.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/create")
    ResponseEntity<User> createUser(@RequestBody User user)
    {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }


    @GetMapping("/all")
    ResponseEntity<List<UserResponse>> getUsers()
    {
        return new ResponseEntity<>(userService.getUsers(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<UserResponse> getUserById(@PathVariable Long id)
    {
        return new ResponseEntity<>(userService.getUsersById(id),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteUserById(@PathVariable Long id)
    {
        userService.deleteUserById(id);
        return new ResponseEntity<>("User deleted successfully",HttpStatus.OK);
    }
}
