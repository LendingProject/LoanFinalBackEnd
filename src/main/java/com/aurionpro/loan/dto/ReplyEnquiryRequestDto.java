package com.aurionpro.loan.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class ReplyEnquiryRequestDto {
	@NotNull(message = "Enquiry ID cannot be null")
    private int enquiryId;

    @NotBlank(message = "Response cannot be blank")
    private String response;
}
