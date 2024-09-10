package com.example.workapp.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PreviousEmployment {
    private String companyName;
    private String jobTitle;
    private Integer yearsWorked;
    private String location;
}
