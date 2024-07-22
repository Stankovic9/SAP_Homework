package com.fioneer.homework.step;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuration class for setting up initial steps in the application.
 * This class is responsible for initializing the steps and saving them
 * into the repository when the application starts.
 */
@Configuration
public class StepConfig {

    /**
     * Creates a CommandLineRunner bean that initializes and saves a list of steps.
     *
     * @param repository the StepRepository to save the steps into.
     * @return a CommandLineRunner that saves the predefined steps.
     */
    @Bean
    CommandLineRunner commandLineRunner(StepRepository repository) {
        return args -> {
            Step step1 = new Step("Step1", 5, 1L, 1);
            Step step2 = new Step("Step2", 3, 3L, 1);
            Step step3 = new Step("Step3", 2, 2L, 1);
            Step step4 = new Step("Step4", 1, 2L, 2);
            Step step5 = new Step("Step5", 5, 2L, 3);
            Step step6 = new Step("Step6", 3, 1L, 2);
            Step step7 = new Step("Step7", 2);
            Step step8 = new Step("Step8", 1);

            repository.saveAll(List.of(step1, step2, step3, step4, step5, step6, step7, step8));
        };
    }
}
