package com.example.workapp.controller;

import com.example.workapp.dto.UserCVDTO;
import com.example.workapp.service.UserProfileService;
import com.example.workapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserProfileService userProfileService;


    @GetMapping("/profile")
    public ResponseEntity<?> findUserByJwtToken(@RequestHeader("Authorization") String jwt ) throws Exception {
        return ResponseEntity.ok(userService.findUserByJwtToken(jwt));
    }

    @PostMapping("/updateCV")
    public ResponseEntity<?> updateCV( @RequestBody UserCVDTO userCVDTO, @RequestHeader("Authorization") String jwt) throws Exception {
        userProfileService.updateCV(userCVDTO, jwt);
        return ResponseEntity.ok("CV updated");
    }


}
