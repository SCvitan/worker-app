package com.example.workapp.controller;

import com.example.workapp.dto.AuthResponse;
import com.example.workapp.dto.LoginRequest;
import com.example.workapp.dto.UserProfileDTO;
import com.example.workapp.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserProfileService userProfileService;




    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody UserProfileDTO request){
        return ResponseEntity.ok(userProfileService.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(userProfileService.login(request));
    }

    @GetMapping("/home")
    public ResponseEntity<?> findAll() throws Exception {
        return ResponseEntity.ok(userProfileService.findAll());
    }

}
