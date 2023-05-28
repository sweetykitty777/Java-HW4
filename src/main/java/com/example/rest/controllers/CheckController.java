package com.example.rest.controllers;

import com.example.rest.models.User;
import com.example.rest.responses.UserResponse;
import com.example.rest.responses.MessageResponse;
import com.example.rest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/check")
public class CheckController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/information")
    @PreAuthorize("hasRole('manager') or hasRole('chief') or hasRole('customer')")
    public ResponseEntity<?> getInfo(@RequestBody String username) {
        if (!userRepository.existsByUsername(username)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("User does not exist"));
        } else {
            User user = userRepository.findByUsername(username).get();
            UserResponse response = new UserResponse(user.getUsername(), user.getEmail(), user.getRole());
            return ResponseEntity.ok(response);
        }
    }
    @GetMapping("/customer")
    @PreAuthorize("hasRole('customer')")
    public String getUser() {
        return "I am customer";
    }

    @GetMapping("/chief")
    @PreAuthorize("hasRole('chief')")
    public String CheckChef() {
        return "I am chief";
    }
    @GetMapping("/manager")
    @PreAuthorize("hasRole('manager')")
    public String CheckManager() {
        return "I am manager";
    }

}
