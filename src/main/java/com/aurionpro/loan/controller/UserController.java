package com.aurionpro.loan.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aurionpro.loan.dto.EmiRequestDto;
import com.aurionpro.loan.dto.EmiResponseDto;
import com.aurionpro.loan.dto.EnquiryRequestDto;
import com.aurionpro.loan.dto.EnquiryResponseDto;
import com.aurionpro.loan.dto.LoanRequestDto;
import com.aurionpro.loan.dto.LoanResponseDto;
import com.aurionpro.loan.dto.LoanSchemeResponseDto;
import com.aurionpro.loan.dto.PageResponse;
import com.aurionpro.loan.dto.PageResponseDto;
import com.aurionpro.loan.dto.RequiredDocumentsRequestDto;
import com.aurionpro.loan.dto.RequiredDocumentsResponseDto;
import com.aurionpro.loan.dto.UserAdminViewResponse;
import com.aurionpro.loan.dto.UserRequestDto;
import com.aurionpro.loan.dto.UserResponseDto;
import com.aurionpro.loan.service.UserService;
import com.aurionpro.loan.service.UserServiceImpl;

@RestController
@RequestMapping("/loanapp")
public class UserController {
	
	@Autowired
	private UserService userService;
	

	
	
	  
	 @PostMapping("/upload")
	    public ResponseEntity<RequiredDocumentsResponseDto> uploadFile(@RequestParam("file") MultipartFile file) {
	        try {
	            // Call the service to handle file upload and get the URL
	            RequiredDocumentsResponseDto response = userService.uploadFile(file);

	            // Return the response with status and URL or error message
	            return ResponseEntity.ok(response);
	        } catch (IOException e) {
	            // Log and return an error response in case of an exception
	            e.printStackTrace();
	            RequiredDocumentsResponseDto errorResponse = new RequiredDocumentsResponseDto();
	            errorResponse.setStatus("FAILURE");
	            errorResponse.setMessage("Error occurred while uploading the file: " + e.getMessage());
	            return ResponseEntity.status(500).body(errorResponse);
	        }
	    }
	
	@PostMapping("/addFile")
	public ResponseEntity<RequiredDocumentsResponseDto> addFileToDatabase(@RequestParam MultipartFile file, RequiredDocumentsRequestDto requiredDocumentsRequestDto){
		return ResponseEntity.ok(userService.addFileToDatabase(requiredDocumentsRequestDto));
	}

	
	@PostMapping("/registeruser")
	private ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto userRequestDto){
		
		return ResponseEntity.ok(userService.addUser(userRequestDto));
	}
	
	
	@GetMapping("/loanschemes")
	public ResponseEntity<PageResponseDto<LoanSchemeResponseDto>> getAllLoanSchemeOfUser(@RequestParam int pageSize,@RequestParam int pageNumber){ 
		   
		  return ResponseEntity.ok(userService.getAllLoanScheme(pageNumber, pageSize)); 
		 }
	
	@PostMapping("/addloan")
	private ResponseEntity<LoanResponseDto> applyLoan(@RequestBody LoanRequestDto loanRequestDto){
	     
	     return ResponseEntity.ok(userService.applyLoan(loanRequestDto));
		
	}
	
	
	
	
	@PostMapping("/payemi")
	private ResponseEntity<EmiResponseDto> emiPayment(@RequestBody EmiRequestDto emiRequestDto){
		
		return ResponseEntity.ok(userService.emiPayment(emiRequestDto));
	}
	
	@GetMapping("/allemi")
	public ResponseEntity<PageResponseDto<EmiResponseDto>> getAllEmis(@RequestParam int pageSize,@RequestParam int pageNumber){ 
		   
		  return ResponseEntity.ok(userService.getAllEmis(pageNumber, pageSize)); 
	}
	
	@PostMapping("/submitquery")
	private ResponseEntity<EnquiryResponseDto> submitQueries(@RequestBody EnquiryRequestDto queryRequestDto){
		
		return ResponseEntity.ok(userService.submitQueries(queryRequestDto));
	}
	
	@GetMapping("/allqueries")
	public ResponseEntity<PageResponseDto<EnquiryResponseDto>> getAllQueries(@RequestParam int pageSize,@RequestParam int pageNumber){ 
		   
		  return ResponseEntity.ok(userService.getAllQueries(pageNumber, pageSize)); 
		 }

    @GetMapping("/users")
    public ResponseEntity<PageResponse<UserAdminViewResponse>> getAllUsers(@RequestParam(defaultValue = "0") int pageNumber,
                                                                           @RequestParam(defaultValue = "10") int pageSize) {
        PageResponse<UserAdminViewResponse> response = userService.getAllUser(pageSize, pageNumber);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/search")
    public ResponseEntity<PageResponse<UserAdminViewResponse>> getUsersByFirstName(@RequestParam(defaultValue = "0") int pageNumber,
                                                                                   @RequestParam(defaultValue = "10") int pageSize,
                                                                                   @RequestParam String firstName) {
        PageResponse<UserAdminViewResponse> response = userService.getUserByFirstName(pageSize, pageNumber, firstName);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/email")
    public ResponseEntity<UserAdminViewResponse> getUserByEmail(@RequestParam String email) {
        UserAdminViewResponse response = userService.getUserByEmail(email);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserAdminViewResponse> getUserById(@PathVariable int id) {
        UserAdminViewResponse response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }
 

}
