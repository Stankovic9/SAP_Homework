package com.fioneer.homework.requestStep;

import com.fioneer.homework.loanRequest.LoanRequest;
import jakarta.persistence.*;

/**
 * Entity class representing a request step.
 * This class is mapped to a database table and includes fields for the step ID,
 * name, duration, order number, status, and associated loan request.
 */
@Entity
@Table
public class RequestStep {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long stepID;

    private String name;
    private Integer durationInDays;
    private Integer orderNumber;
    private String status = "pending";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_request_id", nullable = false)
    private LoanRequest loanRequest;

    /**
     * Default constructor for JPA.
     */
    public RequestStep() {
    }

    /**
     * Constructs a RequestStep with the specified details.
     *
     * @param name           the name of the step.
     * @param durationInDays the duration of the step in days.
     * @param orderNumber    the order number of the step.
     * @param loanRequest    the loan request associated with this step.
     */
    public RequestStep(String name, Integer durationInDays, Integer orderNumber, LoanRequest loanRequest) {
        this.name = name;
        this.durationInDays = durationInDays;
        this.orderNumber = orderNumber;
        this.loanRequest = loanRequest;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public Integer getDurationInDays() {
        return durationInDays;
    }

    public Long getStepID() {
        return stepID;
    }

    public String getRequestLoanName() {
        return loanRequest.getLoanName();
    }

    public Long getRequestLoanId() {
        return loanRequest.getLoanRequestId();
    }

    public void setDurationInDays(Integer durationInDays) {
        this.durationInDays = durationInDays;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStepID(Long stepID) {
        this.stepID = stepID;
    }

    public void setLoanRequest(LoanRequest loanRequest) {
        this.loanRequest = loanRequest;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
