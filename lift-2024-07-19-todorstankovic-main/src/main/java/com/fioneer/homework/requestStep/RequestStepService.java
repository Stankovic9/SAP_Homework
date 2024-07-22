package com.fioneer.homework.requestStep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing request steps.
 * This service provides methods for retrieving all request steps.
 */
@Service
public class RequestStepService {

    @Autowired
    private RequestStepRepository requestStepRepository;

    public List<RequestStep> getAll() {
        return requestStepRepository.findAll();
    }
}
