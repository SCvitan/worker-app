package com.example.workapp.repository;

import com.example.workapp.entity.TruckerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckerProfileRepo extends JpaRepository<TruckerProfile, Long> {
}
