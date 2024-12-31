package com.aurionpro.loan.repository;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.aurionpro.loan.entity.MissedPayment;
import com.aurionpro.loan.entity.NPA;
import com.aurionpro.loan.entity.User;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AutomaticallyNpaCleanupService {
	private final NpaRespository npaRepository;
    private final MissedPayementRepository missedPaymentRepository;

    // Runs daily at 1 AM, for example
    @Scheduled(cron = "0 0/2 * * * ?") 
    public void cleanupNpaUsersIfAllPaid() {
        // 1) Fetch all NPA records that are not deleted
        List<NPA> activeNpas = npaRepository.findByDeletedFalse();

        activeNpas.forEach(npa -> {
            User user = npa.getUser();
            // 2) Check how many active missed payments remain
            List<MissedPayment> activeMissed = missedPaymentRepository.findByUserAndIsPaidFalseAndDeletedFalse(user);

            // 3) If none, remove user from NPA and soft-delete all paid missed payments
            if (activeMissed.isEmpty()) {
                // a) Soft delete the NPA record
                npa.setDeleted(true);
                npaRepository.save(npa);

                
            }
        });
    }
	
	
}
