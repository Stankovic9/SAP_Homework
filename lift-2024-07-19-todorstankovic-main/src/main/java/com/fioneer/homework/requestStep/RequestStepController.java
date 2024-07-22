package com.fioneer.homework.requestStep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing request steps.
 * This controller provides an endpoint for retrieving all request steps.
 */
@RestController
@RequestMapping("/requestStep")
public class RequestStepController {

    @Autowired
    private RequestStepService requestStepService;

    @GetMapping("/getAll")
    public List<RequestStep> getAllRequestSteps() {
        return requestStepService.getAll();
    }
}
