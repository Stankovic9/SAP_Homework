package com.fioneer.homework.step;

import jakarta.persistence.*;

/**
 * Entity class representing a step in a loan process.
 * This class is mapped to a database table and includes fields for the step ID,
 * name, duration, order number, status, and associated loan type ID.
 */
@Entity
@Table
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long stepID;

    private String name;
    private Integer durationInDays;
    private Integer orderNumber;
    private String status = "pending";

    @Column(name = "loan_type_id")
    private Long loanTypeId;

    /**
     * Default constructor for JPA.
     */
    public Step() {
    }

    /**
     * Constructs a Step with the specified name and duration.
     *
     * @param name           the name of the step.
     * @param durationInDays the duration of the step in days.
     */
    public Step(String name, Integer durationInDays) {
        this.name = name;
        this.durationInDays = durationInDays;
    }

    /**
     * Constructs a Step with the specified details.
     *
     * @param name           the name of the step.
     * @param durationInDays the duration of the step in days.
     * @param loanTypeId     the ID of the associated loan type.
     * @param orderNumber    the order number of the step.
     */
    public Step(String name, Integer durationInDays, Long loanTypeId, Integer orderNumber) {
        this.name = name;
        this.durationInDays = durationInDays;
        this.loanTypeId = loanTypeId;
        this.orderNumber = orderNumber;
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

    public Long getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(Long loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
