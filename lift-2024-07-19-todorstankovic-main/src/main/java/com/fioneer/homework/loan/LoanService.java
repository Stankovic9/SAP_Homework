package com.fioneer.homework.loan;

import com.fioneer.homework.step.Step;
import com.fioneer.homework.step.StepRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing loan types and their associated steps.
 * This service provides methods for retrieving, creating, and updating loan types,
 * as well as associating steps with loan types.
 */
@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private StepRepository stepRepository;

    /**
     * Returns a list of all loan types.
     *
     * @return a list of all loan types.
     */
    public List<LoanType> getLoans(){
        return loanRepository.findAll();
    }

    /**
     * Finds a loan type by its name.
     *
     * @param name the name of the loan type to find.
     * @return the LoanType object if found, or null if not found.
     */
    public LoanType getLoanByName(String name){
        return loanRepository.findByName(name).orElse(null);
    }

    /**
     * Checks if a loan with the given name exists.
     *
     * @param name the name of the loan type to check.
     * @return true if the loan name exists, false otherwise.
     */
    public Boolean loanNameExists(String name){
        return getLoanByName(name) != null;
    }

    /**
     * Associates a list of step IDs with a loan type.
     * If the step is not already associated with another loan, it is connected to the loan type.
     *
     * @param stepIds  the list of step IDs to associate with the loan type.
     * @param loanType the loan type to associate the steps with.
     */
    @Transactional
    public void addStepsToLoanType(List<Long> stepIds, LoanType loanType) {
        int orderNum = 1;
        for (Long stepId : stepIds) {
            Step step = stepRepository.findById(stepId)
                    .orElseThrow(() -> new RuntimeException("Step not found"));

            if (step.getLoanTypeId() != null && !step.getLoanTypeId().equals(loanType.getLoanID())) {
                continue;
            }
            step.setLoanTypeId(loanType.getLoanID());
            step.setOrderNumber(orderNum);
            orderNum++;
            stepRepository.save(step);
        }
        loanRepository.save(loanType);
    }

    /**
     * Retrieves the details of a loan type by its name, including its associated steps.
     *
     * @param loanName the name of the loan type to retrieve details for.
     * @return a string containing the details of the requested loan type.
     */
    public String getLoanDetails(String loanName) {
        LoanType tempLoan = getLoanByName(loanName);
        if (tempLoan == null) {
            return "Loan not found";
        }
        StringBuilder listOfSteps = new StringBuilder();
        for (Step step : tempLoan.getProcedure()) {
            listOfSteps.append(step.getName()).append(", ");
        }
        return  "Your loan: " + loanName + " , your loan duration: "  + getDuration(tempLoan)
                + " , number of steps: " + tempLoan.getProcedure().size() + " , Here are the steps: " + listOfSteps;
    }

    /**
     * Calculates the total duration of the loan based on its associated steps.
     *
     * @param loan the loan type to calculate the duration for.
     * @return the total duration of the loan in days.
     */
    public Integer getDuration(LoanType loan){
        return loan.getProcedure().stream()
                .mapToInt(Step::getDurationInDays)
                .sum();
    }

    /**
     * Submits a new loan type, usually without steps connected, to the database.
     *
     * @param myLoan the loan type to submit.
     */
    public void makeNewLoan(LoanType myLoan) {
        loanRepository.save(myLoan);
    }
}
