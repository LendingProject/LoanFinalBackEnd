package com.aurionpro.loan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.loan.entity.MissedPayment;
import com.aurionpro.loan.entity.NPA;
import com.aurionpro.loan.entity.User;
import com.aurionpro.loan.repository.MissedPayementRepository;
import com.aurionpro.loan.repository.NpaRespository;

@Service
public class EmiServiceImpl {
	@Autowired
	private  MissedPayementRepository missedPaymentRepository;
	@Autowired
    private  NpaRespository npaRepository;
	
	public void payMissedPayment(User user, Long missedPaymentId) {
        // 1) Fetch the missed payment
        MissedPayment mp = missedPaymentRepository.findById(missedPaymentId)
                .orElseThrow(() -> new RuntimeException("MissedPayment not found with ID: " + missedPaymentId));

        

        // 2) Mark it paid & soft-delete
        mp.setPaid(true);
        mp.setDeleted(true);
        missedPaymentRepository.save(mp);

        // 3) Check if user has any other active missed payments
        List<MissedPayment> activeMissed = missedPaymentRepository.findByUserAndIsPaidFalseAndDeletedFalse(user);
        if (activeMissed.isEmpty()) {
            // 4) Remove user from NPA
            NPA npaRecord = npaRepository.findByUserAndDeletedFalse(user);
            if (npaRecord != null) {
                npaRecord.setDeleted(true);
                npaRepository.save(npaRecord);
            }
        }
    }

}
