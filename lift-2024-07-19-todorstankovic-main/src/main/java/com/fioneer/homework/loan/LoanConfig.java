package com.fioneer.homework.loan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuration class for setting up initial loan types in the application.
 * This class is responsible for initializing the loan types and saving them
 * into the repository when the application starts.
 */
@Configuration
public class LoanConfig {

    /**
     * Creates a CommandLineRunner bean that initializes and saves a list of loan types.
     *
     * @param repository the LoanRepository to save the loan types into.
     * @return a CommandLineRunner that saves the predefined loan types.
     */
    @Bean
    CommandLineRunner commandLineRunner1(LoanRepository repository) {
        return args -> {
            LoanType loan1 = new LoanType("CashLoan");
            LoanType loan2 = new LoanType("StudentLoan");
            LoanType loan3 = new LoanType("RealEstateLoan");

            repository.saveAll(List.of(loan1,loan2,loan3));
        };

    }
}
