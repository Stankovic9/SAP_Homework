package com.fioneer.homework.loanRequest;

import com.fioneer.homework.requestStep.RequestStep;
import com.fioneer.homework.loan.LoanType;
import jakarta.persistence.*;

import java.util.List;

/**
 * Entity class representing a loan request.
 * This class is mapped to a database table and includes fields for the loan request ID,
 * associated loan type, request steps, and applicant details.
 */
@Entity
@Table
public class LoanRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long loanRequestId;

    @OneToMany(mappedBy = "loanRequest", cascade = CascadeType.ALL)
    private List<RequestStep> procedure;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loan_type_id")
    private LoanType loanType;

    private String loanName;
    private String firstName;
    private String lastName;
    private Integer loanAmount;

    private String status = "processing";

    /**
     * Default constructor for JPA.
     */
    public LoanRequest() {}

    /**
     * Constructs a LoanRequest with the specified details.
     *
     * @param firstName  the first name of the applicant.
     * @param lastName   the last name of the applicant.
     * @param loanAmount the requested loan amount.
     * @param loanType   the type of loan requested.
     * @param loanName   the name of the loan.
     */
    public LoanRequest(String firstName, String lastName, Integer loanAmount, LoanType loanType, String loanName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.loanAmount = loanAmount;
        this.loanType = loanType;
        this.loanName = loanName;
    }

    public Long getLoanRequestId() {
        return loanRequestId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getLoanAmount() {
        return loanAmount;
    }

    public String getLoanName() {
        return loanName;
    }

    public String getStatus() {
        return status;
    }

    public List<RequestStep> getProcedure() {
        return procedure;
    }

    public void setLoanRequestId(Long loanRequestId) {
        this.loanRequestId = loanRequestId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLoanAmount(Integer loanAmount) {
        this.loanAmount = loanAmount;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
