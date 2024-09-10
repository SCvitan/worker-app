package com.example.workapp.entity;

import com.example.workapp.enums.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)  // Joined table strategy for inheritance
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.ROLE_USER;

    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Integer age;
    private String placeOfResidence;
    private String nationality;

    @Enumerated(EnumType.STRING)
    private EducationLevel educationLevel;
    private String nameOfEducationalFacility;

    private Boolean driversLicence;
    @Enumerated(EnumType.STRING)
    private Languages languages;

    @Enumerated(EnumType.STRING)
    private Profession profession;  // Selected profession (TRUCKER, CONSTRUCTION_WORKER, CLEANER)

    // Relations to Profession-specific Entities
    @OneToOne(mappedBy = "userProfile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private TruckerProfile truckerProfile;

    @OneToOne(mappedBy = "userProfile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ConstructionWorkerProfile constructionWorkerProfile;

    @OneToOne(mappedBy = "userProfile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CleanerProfile cleanerProfile;

}
