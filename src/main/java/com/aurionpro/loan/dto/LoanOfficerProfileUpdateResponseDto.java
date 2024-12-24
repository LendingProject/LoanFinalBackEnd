package com.aurionpro.loan.dto;


import java.sql.Date;

import com.aurionpro.loan.entity.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanOfficerProfileUpdateResponseDto {

    private int officerId;
    private String firstName;
    private String lastName;
    private Date dob;
    private long contactNumber;
    private String pancardNumber;
    private Gender gender;
    private boolean deleted;
}

