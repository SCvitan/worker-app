package com.example.workapp.service;

import com.example.workapp.entity.UserProfile;

public interface UserService {

    public UserProfile findUserByJwtToken(String jwt) throws Exception;

    public UserProfile findUserByEmail(String email) throws Exception;




}
