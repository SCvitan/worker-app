package com.example.workapp.entity;

import com.example.workapp.enums.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TruckerProfile {
    @Id
    private Long userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserProfile userProfile;

    @Enumerated(EnumType.STRING)
    private ContractType contractType;


    private Integer yearsOfExperience;
    private Boolean hazardousMaterialsCertified;

    @ElementCollection
    @CollectionTable(name = "previous_employments", joinColumns = @JoinColumn(name = "user_id"))
    private List<PreviousEmployment> previousEmployments;

    @ElementCollection(targetClass = StateOfEmploymentOfInterest.class)
    @CollectionTable(name = "trucker_states_of_interest", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "state_of_interest")
    private Set<StateOfEmploymentOfInterest> statesOfEmploymentOfInterest = new HashSet<>();

    private Boolean willingToRelocate;
    private Boolean loadingUnloadingGoods;

    @ElementCollection(targetClass = DriversLicence.class)
    @CollectionTable(name = "drivers_license_types", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "drivers_license")
    private Set<DriversLicence> driversLicences = new HashSet<>();

    @ElementCollection(targetClass = JobInterest.class)
    @CollectionTable(name = "driver_job_interest", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "job_interest")
    private Set<JobInterest> jobInterest = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private AccommodationCostsByEmployer accommodationCostsByEmployer;

    private Integer expectedNETSalary;




}

