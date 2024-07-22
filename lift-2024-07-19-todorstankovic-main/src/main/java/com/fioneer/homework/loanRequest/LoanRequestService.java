package com.fioneer.homework.loanRequest;

import com.fioneer.homework.requestStep.RequestStep;
import com.fioneer.homework.requestStep.RequestStepRepository;
import com.fioneer.homework.loan.LoanRepository;
import com.fioneer.homework.loan.LoanType;
import com.fioneer.homework.step.Step;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing loan requests and their associated steps.
 * This service provides methods for creating, processing, and retrieving loan requests,
 * as well as verifying and updating the status of loan request steps.
 */
@Service
public class LoanRequestService {

    @Autowired
    private RequestStepRepository requestStepRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    /**
     * Returns a list of all loan requests.
     *
     * @return a list of all loan requests.
     */
    public List<LoanRequest> getLoans() {
        return loanRequestRepository.findAll();
    }

    /**
     * Creates a new loan request and associates it with the steps from the corresponding loan type.
     *
     * @param fName    the first name of the applicant.
     * @param lName    the last name of the applicant.
     * @param amount   the requested loan amount.
     * @param loanName the name of the loan type.
     * @return a message indicating the result of the operation.
     */
    @Transactional
    public String makeNewLoanRequest(String fName, String lName, Integer amount, String loanName) {
        LoanType tempLoan = loanRepository.findByName(loanName).orElse(null);

        if (tempLoan == null) {
            throw new RuntimeException("Loan type not found: " + loanName);
        }

        LoanRequest newRequest = new LoanRequest(fName, lName, amount, tempLoan, tempLoan.getName());
        loanRequestRepository.save(newRequest);

        List<Step> steps = tempLoan.getProcedure();
        for (Step step : steps) {
            RequestStep requestStep = new RequestStep(step.getName(), step.getDurationInDays(), step.getOrderNumber(), newRequest);
            requestStepRepository.save(requestStep);
        }
        return loanName;
    }

    /**
     * Processes the next step in a loan request by updating its status.
     *
     * @param loanRequestId the ID of the loan request to process.
     * @param status        the new status of the step.
     */
    public void processStep(Long loanRequestId, String status) {
        LoanRequest myRequest = loanRequestRepository.findById(loanRequestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        for (RequestStep requestStep : myRequest.getProcedure()) {
            if ("pending".equals(requestStep.getStatus())) {
                requestStep.setStatus(status);
                loanRequestRepository.save(myRequest);
                break;
            }
        }
    }

    /**
     * Returns the next step in a loan request.
     *
     * @param loanRequestId the ID of the loan request to find the next step for.
     * @return the next step in the loan request, or null if no next step is available.
     */
    public RequestStep getNextStep(Long loanRequestId) {
        LoanRequest myRequest = loanRequestRepository.findById(loanRequestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        for (RequestStep requestStep : myRequest.getProcedure()) {
            if ("pending".equals(requestStep.getStatus())) {
                return requestStep;
            }
        }
        return null;
    }

    /**
     * Checks the status of all steps in a loan request and updates the loan request's status accordingly.
     *
     * @param loanRequestId the ID of the loan request to check the status for.
     * @return a message indicating the result of the status check.
     */
    public String checkStatus(Long loanRequestId) {
        LoanRequest myRequest = loanRequestRepository.findById(loanRequestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        boolean allSuccessful = true;
        for (RequestStep requestStep : myRequest.getProcedure()) {
            String status = requestStep.getStatus();
            if ("failed".equals(status)) {
                myRequest.setStatus("rejected");
                loanRequestRepository.save(myRequest);
                return "This loan has been rejected";
            } else if ("pending".equals(status)) {
                allSuccessful = false;
            } else if (!"successful".equals(status)) {
                return "Status might have a typo";
            }
        }
        if (allSuccessful) {
            myRequest.setStatus("approved");
            loanRequestRepository.save(myRequest);
            return "This loan has been approved";
        }
        return "This loan is still pending";
    }

    /**
     * Verifies that the given status is one of the supported statuses.
     *
     * @param status the status to verify.
     * @return true if the status is "successful" or "failed", false otherwise.
     */
    public boolean verifyStatus(String status) {
        return "successful".equals(status) || "failed".equals(status);
    }
}
