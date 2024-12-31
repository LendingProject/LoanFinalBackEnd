package com.aurionpro.loan.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.loan.entity.User;
import com.aurionpro.loan.repository.UserRepository;
import com.aurionpro.loan.service.EmiServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/loanapp/emi")
@RequiredArgsConstructor
public class EmiPaymentController {

    private final EmiServiceImpl emiPaymentService;
    private final UserRepository userRepository;

    @PostMapping("/pay-missed")
    public ResponseEntity<String> payMissedEmi(@RequestParam("userId") int userId,
                                               @RequestParam("missedPaymentId") long missedPaymentId) {
        // 1) Fetch user from DB
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // 2) Call service to mark the missed payment as paid
        emiPaymentService.payMissedPayment(user, missedPaymentId);

        // 3) Return response
        return ResponseEntity.ok(
            "Missed payment with ID " + missedPaymentId 
            + " paid successfully for user ID " + userId
            + ". User removed from NPA if no active missed EMIs remain."
        );
    }
}