package com.fioneer.homework.loanRequest;

import com.fioneer.homework.requestStep.RequestStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing loan requests.
 * This controller provides endpoints for creating new loan requests, retrieving all loan requests,
 * processing loan requests, and finding the next step in a loan request.
 */
@RestController
@RequestMapping("/loanRequest")
public class LoanRequestController {

    @Autowired
    private LoanRequestService loanRequestService;

    /**
     * Creates a new loan request.
     *
     * @param fName    the first name of the applicant.
     * @param lName    the last name of the applicant.
     * @param amount   the requested loan amount.
     * @param loanName the name of the loan.
     */
    @PostMapping("/makeNew/{fName}/{lName}/{amount}/{loanName}")
    public void makeNewLoanRequest(@PathVariable String fName, @PathVariable String lName, @PathVariable Integer amount, @PathVariable String loanName) {
        loanRequestService.makeNewLoanRequest(fName, lName, amount, loanName);
    }

    /**
     * Retrieves all loan requests.
     *
     * @return a list of all loan requests.
     */
    @GetMapping("/getAll")
    public List<LoanRequest> getAllLoanRequests() {
        return loanRequestService.getLoans();
    }

    /**
     * Processes a loan request by updating its status.
     *
     * @param loanRequestId the ID of the loan request to process.
     * @param status        the new status of the loan request.
     * @return a message indicating the result of the operation.
     */
    @PutMapping("/processRequest/{loanRequestId}/{status}")
    public String processLoanRequest(@PathVariable Long loanRequestId, @PathVariable String status) {
        if (loanRequestService.verifyStatus(status)) {
            loanRequestService.processStep(loanRequestId, status);
        } else {
            return "You selected a status that isn't supported, try 'successful' or 'failed'";
        }
        return loanRequestService.checkStatus(loanRequestId);
    }

    /**
     * Finds the next step in a loan request.
     *
     * @param loanRequestId the ID of the loan request to find the next step for.
     * @return a message indicating the name of the next step, or that no next step is available.
     */
    @GetMapping("/findNextStep/{loanRequestId}")
    public String findNextStep(@PathVariable Long loanRequestId) {
        RequestStep tempStep = loanRequestService.getNextStep(loanRequestId);
        if (tempStep == null) {
            return "No next step available";
        } else {
            return "Next step name: " + tempStep.getName();
        }
    }
}
