package com.fioneer.homework.loan;

import com.fioneer.homework.step.Step;
import jakarta.persistence.*;

import java.util.List;

/**
 * Entity class representing a loan type.
 * This class is mapped to a database table and includes fields for the loan name,
 * loan ID, and the associated procedure steps.
 */
@Entity
@Table
public class LoanType {

    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long loanID;

    @OneToMany(mappedBy = "loanTypeId", cascade = CascadeType.ALL)
    private List<Step> procedure;

    /**
     * Default constructor for JPA.
     */
    public LoanType() {
    }

    public LoanType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Step> getProcedure() {
        return procedure;
    }

    public Long getLoanID() {
        return loanID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProcedure(List<Step> procedure) {
        this.procedure = procedure;
    }

    public void setLoanID(Long loanID) {
        this.loanID = loanID;
    }
}
