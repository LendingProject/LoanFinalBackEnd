package com.aurionpro.loan.controller;

import java.io.File;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("loanapp")
public class BatchController {

    @Autowired
    private org.springframework.batch.core.launch.JobLauncher jobLauncher;

    @Autowired
    private Job exportNpaAndMissedPaymentJob;

   
    @GetMapping("/generate-and-download")
    public ResponseEntity<?> generateAndDownloadFile() {

        try {
            // 1) Run the batch job (synchronously) with a unique parameter so it can run multiple times
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("startAt", System.currentTimeMillis())
                    .toJobParameters();

            // The jobLauncher.run(...) call blocks until the job is done (by default).
            JobExecution execution = jobLauncher.run(exportNpaAndMissedPaymentJob, jobParameters);

            // 2) Check the job status
            if (!execution.getStatus().isUnsuccessful()) {
                // The job succeeded or is at least not failed.
                // 3) Now attempt to serve the file that was created by the batch job.
                File file = new File("/tmp/npa.csv"); // Adjust to match your writer path
                if (!file.exists()) {
                    return ResponseEntity.notFound().build();
                }

                Resource resource = new FileSystemResource(file);
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"npa.csv\"")
                        .contentLength(file.length())
                        .contentType(MediaType.parseMediaType("text/csv"))
                        .body(resource);

            } else {
                // The job ended in an unsuccessful status (FAILED, STOPPED, etc.)
                return ResponseEntity.badRequest().body("Batch job did not complete successfully.");
            }

        } catch (org.springframework.batch.core.JobExecutionException e) {
            e.printStackTrace();
            // 4) If there's an exception, return an error response
            return ResponseEntity.internalServerError().body("Failed to run batch job: " + e.getMessage());
        }
    }
}