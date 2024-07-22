package com.fioneer.homework.step;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing steps.
 * This controller provides an endpoint for retrieving all steps.
 */
@RestController
@RequestMapping("/step")
public class StepController {

    @Autowired
    private StepService stepService;

    @GetMapping("/getAll")
    public List<Step> getAllSteps() {
        return stepService.getSteps();
    }
}
