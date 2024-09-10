package com.example.workapp.dto;

import com.example.workapp.entity.PreviousEmployment;
import com.example.workapp.enums.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCVProfileDTO {
    // Common fields
    private String firstName;
    private String lastName;
    private Integer age;
    private Gender gender;
    private String nationality;
    private String placeOfResidence;
    private Profession profession;
    private Languages languages;
    private EducationLevel educationLevel;
    private String nameOfEducationalFacility;
    private Boolean driversLicence;

    // Common Profession Fields
    private Integer yearsOfExperience;

    // Trucker-specific fields
    private DriversLicence truckLicenseType;
    private Boolean hazardousMaterialsCertified;
    private List<PreviousEmployment> previousEmployments;
    private Set<DriversLicence> driversLicences;
    private Set<JobInterest> jobInterest;
    private ContractType contractType;
    private Set<StateOfEmploymentOfInterest> statesOfEmploymentOfInterest;
    private Boolean willingToRelocate;
    private Boolean loadingUnloadingGoods;
    private AccommodationCostsByEmployer accommodationCostsByEmployer;
    private Integer expectedNETSalary;

    // Construction Worker-specific fields
    private String specialization;
    private Boolean hasOSHACompliance;

    // Cleaner-specific fields
    private String cleaningSpecialty;
    private Boolean certifiedForHazardousWaste;

}
