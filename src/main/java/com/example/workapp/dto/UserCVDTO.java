package com.example.workapp.dto;


import com.example.workapp.enums.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCVDTO {

    private String firstName;
    private String lastName;
    private Gender gender;
    private Integer age;
    private String placeOfBirth;
    private String placeOfResidence;
    private String nationality;

    private EducationLevel educationLevel;
    private String nameOfEducationalFacility;
    private DriversLicence driversLicence;
    private Languages languages;

    private Profession profession;



}
