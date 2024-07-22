package com.fioneer.homework.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing loan types.
 * This controller provides endpoints for creating new loans,
 * retrieving loan details, and listing all loans.
 */
@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private LoanService loanService;

    /**
     * Creates a new loan type and associates it with a list of procedure steps.
     *
     * @param loanName  the name of the new loan type.
     * @param procedure the list of steps to be associated with the loan type.
     * @return a message indicating the result of the operation.
     */
    @PostMapping("makeNew/{loanName}/{procedure}/")
    public String newLoan(@PathVariable String loanName, List<Long> procedure){

        if (loanService.loanNameExists(loanName) == true){
            return "Loan name already exists";
        } else {
            LoanType myNewLoan = new LoanType(loanName);
            loanService.makeNewLoan(myNewLoan);
            loanService.addStepsToLoanType(procedure,myNewLoan);
            return "Success";
        }
    }

    /**
     * Retrieves the details of a loan type by its name.
     *
     * @param loanName the name of the loan type to retrieve.
     * @return the details of the requested loan type.
     */
    @GetMapping("/{loanName}/details")
    public String getLoanDetailsByName(@PathVariable String loanName){
        return loanService.getLoanDetails(loanName);
    }

    /**
     * Retrieves a list of all loan types.
     *
     * @return a list of all loan types.
     */
    @GetMapping("/getAll")
    public List<LoanType> getAllLoans(){
        return loanService.getLoans();
    }
}
