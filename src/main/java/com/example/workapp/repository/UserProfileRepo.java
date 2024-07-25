package com.example.workapp.repository;

import com.example.workapp.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepo extends JpaRepository<UserProfile, Long> {

    boolean existsByEmail(String email);

    UserProfile findByEmail(String email);

}
