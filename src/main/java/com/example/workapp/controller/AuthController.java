package com.example.workapp.controller;

import com.example.workapp.dto.AuthResponse;
import com.example.workapp.dto.LoginRequest;
import com.example.workapp.dto.AuthRequest;
import com.example.workapp.security.JwtProvider;
import com.example.workapp.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private JwtProvider jwtProvider;


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody AuthRequest request){
        AuthResponse authResponse = userProfileService.registerUser(request);
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, authResponse.getJwt())
                .body(authResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){
        AuthResponse authResponse = userProfileService.login(request);
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, authResponse.getJwt())
                .body(authResponse);
    }

}
