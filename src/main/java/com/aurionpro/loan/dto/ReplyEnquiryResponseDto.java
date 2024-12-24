package com.aurionpro.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data

public class ReplyEnquiryResponseDto {

    private int enquiryId;      
    private String question;    
    private String response;  
    private String status; 
}