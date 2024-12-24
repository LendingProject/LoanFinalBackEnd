package com.aurionpro.loan.dto;

import java.sql.Date;

import com.aurionpro.loan.entity.Gender;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanOfficerProfileUpdateRequestDto {

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Za-z]+(?:[ -'][A-Za-z]+)*$", message = "Enter valid first name")
    private String firstName;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Za-z]+(?:[ -'][A-Za-z]+)*$", message = "Enter valid last name")
    private String lastName;

    @NotNull
    @NotBlank
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
        message = "Enter a valid password"
    )
    private String password;

    @NotNull(message = "DOB cannot be null")
    private Date dob;

    @NotNull
    @Digits(integer = 10, fraction = 0, message = "Enter a valid 10-digit contact number")
    private Long contactNumber;

    @NotNull
    @NotBlank
    @Pattern(
        regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$",
        message = "Enter a valid PAN card number"
    )
    private String pancardNumber;

    @NotNull(message = "Gender cannot be null")
    private Gender gender;
}
