package com.fioneer.homework.step;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class for managing steps.
 * This service provides methods for retrieving all steps.
 */
@Service
public class StepService {

    @Autowired
    private StepRepository stepRepository;

    public List<Step> getSteps() {
        return stepRepository.findAll();
    }
}
