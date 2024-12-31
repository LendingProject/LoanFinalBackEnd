package com.aurionpro.loan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.loan.dto.NpaUserDto;
import com.aurionpro.loan.dto.PageResponse;
import com.aurionpro.loan.entity.User;
import com.aurionpro.loan.repository.UserRepository;
import com.aurionpro.loan.service.NpaServiceImpl;

@RestController
@RequestMapping("/loanapp/npa")
public class NpaController {

	@Autowired
    private NpaServiceImpl npaService;

    @Autowired
    private UserRepository userRepository;

   
    @GetMapping("/run-check")
    public ResponseEntity<String> runNpaCheck() {
        npaService.checkAndMarkNpaUsers();
        return ResponseEntity.ok("NPA check executed successfully!");
    }

   
    @PostMapping("/remove")
    public ResponseEntity<String> removeUserFromNpa(@RequestParam("userId") int userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        npaService.removeUserFromNpa(user);
        return ResponseEntity.ok("User with ID " + userId + " removed from NPA (soft delete).");
    }
    
    @GetMapping("/list")
    public ResponseEntity<PageResponse<NpaUserDto>> getAllNpaUsers(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        PageResponse<NpaUserDto> response =
                npaService.getAllNpaUsersWithMissedPayments(pageNumber, pageSize);
        return ResponseEntity.ok(response);
    }
    
    
    
    
    
}