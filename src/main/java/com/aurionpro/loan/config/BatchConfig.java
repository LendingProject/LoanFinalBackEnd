package com.aurionpro.loan.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;


import com.aurionpro.loan.entity.MissedPayment;
import com.aurionpro.loan.entity.NPA;

/**
 * Example Spring Batch config that:
 *  1) Reads from 'npa' table -> writes to npa.csv
 *  2) Reads from 'missed_payments' table -> writes to missed_payments.csv
 */
@Configuration
public class BatchConfig {

    // ------------------------------------------------------------------
    // TRANSACTION MANAGER
    // ------------------------------------------------------------------
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    // ------------------------------------------------------------------
    // NPA STEP
    //  - Reader
    //  - Processor
    //  - Writer
    //  - Step
    // ------------------------------------------------------------------
    
    /** Reads Npa records from DB */
    @Bean
    public JdbcCursorItemReader<NPA> npaReader(DataSource dataSource) {
        JdbcCursorItemReader<NPA> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        // Adjust query to match your 'npa' table columns
        reader.setSql("SELECT npa_id, npa_date, deleted, user_id FROM npa");
        reader.setRowMapper(new BeanPropertyRowMapper<>(NPA.class));
        reader.setName("npaReader");
        return reader;
    }

    /** Simple pass-through processor for Npa */
    @Bean
    public NpaProcessor npaProcessor() {
        return new NpaProcessor();
    }

    /** Writes Npa records to CSV (npa.csv) */
    @Bean
    public FlatFileItemWriter<NPA> npaWriter() {
        FlatFileItemWriter<NPA> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource("npa.csv"));
        writer.setAppendAllowed(false); // Overwrite each run

        DelimitedLineAggregator<NPA> aggregator = new DelimitedLineAggregator<>();
        aggregator.setDelimiter(",");

        // If your Npa entity has: Long id; Date npaDate; boolean deleted; User user;
        // and you want user.id specifically, you can reference "user.id"
        BeanWrapperFieldExtractor<NPA> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[] {"id", "npaDate", "deleted"});
        aggregator.setFieldExtractor(fieldExtractor);

        writer.setLineAggregator(aggregator);

        return writer;
    }

    /**
     * Step definition for NPA export:
     *  - chunk(2) means every 2 records, transaction is committed.
     */
    @Bean
    public Step npaStep(
        JobRepository jobRepository,
        DataSourceTransactionManager transactionManager,
        JdbcCursorItemReader<NPA> npaReader,
        NpaProcessor npaProcessor,
        FlatFileItemWriter<NPA> npaWriter
    ) {
        return new StepBuilder("npaStep", jobRepository)
            .<NPA, NPA>chunk(2, transactionManager)
            .reader(npaReader)
            .processor(npaProcessor)
            .writer(npaWriter)
            .build();
    }

    // ------------------------------------------------------------------
    // MISSED PAYMENT STEP
    //  - Reader
    //  - Processor
    //  - Writer
    //  - Step
    // ------------------------------------------------------------------
    
    /** Reads MissedPayment records from DB */
    @Bean
    public JdbcCursorItemReader<MissedPayment> missedPaymentReader(DataSource dataSource) {
        JdbcCursorItemReader<MissedPayment> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        // Adjust to match your 'missed_payments' table columns
        reader.setSql("SELECT missed_payment_id, emi_amount, due_date, is_paid, deleted, user_id, loan_id FROM missed_payments");
        reader.setRowMapper(new BeanPropertyRowMapper<>(MissedPayment.class));
        reader.setName("missedPaymentReader");
        return reader;
    }

    /** Simple pass-through processor for MissedPayment */
    @Bean
    public MissedPaymentProcessor missedPaymentProcessor() {
        return new MissedPaymentProcessor();
    }

    /** Writes MissedPayment records to CSV (missed_payments.csv) */
    @Bean
    public FlatFileItemWriter<MissedPayment> missedPaymentWriter() {
        FlatFileItemWriter<MissedPayment> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource("missed_payments.csv"));
        writer.setAppendAllowed(false);

        DelimitedLineAggregator<MissedPayment> aggregator = new DelimitedLineAggregator<>();
        aggregator.setDelimiter(",");

        // If your MissedPayment entity has: Long id; double emiAmount; Date dueDate; boolean paid; boolean deleted; User user; LoanRequest loanRequest;
        // and you want "user.id" / "loanRequest.id" specifically, you can do so:
        BeanWrapperFieldExtractor<MissedPayment> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[] {
            "id", "emiAmount", "dueDate", "paid", "deleted"
        });
        aggregator.setFieldExtractor(fieldExtractor);

        writer.setLineAggregator(aggregator);

        return writer;
    }

    /**
     * Step definition for MissedPayment export:
     *  - chunk(2) means every 2 records, transaction is committed.
     */
    @Bean
    public Step missedPaymentStep(
        JobRepository jobRepository,
        DataSourceTransactionManager transactionManager,
        JdbcCursorItemReader<MissedPayment> missedPaymentReader,
        MissedPaymentProcessor missedPaymentProcessor,
        FlatFileItemWriter<MissedPayment> missedPaymentWriter
    ) {
        return new StepBuilder("missedPaymentStep", jobRepository)
            .<MissedPayment, MissedPayment>chunk(2, transactionManager)
            .reader(missedPaymentReader)
            .processor(missedPaymentProcessor)
            .writer(missedPaymentWriter)
            .build();
    }

    // ------------------------------------------------------------------
    // JOB: combine both steps
    // ------------------------------------------------------------------
    @Bean
    public Job exportNpaAndMissedPaymentJob(
        JobRepository jobRepository,
        Step npaStep,
        Step missedPaymentStep,
        JobCompletionNotificationListener listener
    ) {
        return new JobBuilder("exportNpaAndMissedPaymentJob", jobRepository)
            .listener(listener)
            .start(npaStep)
            .next(missedPaymentStep)
            .build();
    }

}