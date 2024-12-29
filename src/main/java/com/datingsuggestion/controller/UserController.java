package com.datingsuggestion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datingsuggestion.entity.User;
import com.datingsuggestion.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	
    @Autowired
    private UserService userService;

    @GetMapping("/{id}/matches")
    public List<User> getTopMatches(@PathVariable Long id) {
        return userService.getTopMatches(id);
    }
    
 // Endpoint for creating a new user
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);  // Save the user
        return ResponseEntity.ok("User successfully registered! Your ID is: " + savedUser.getId());
    }
}
