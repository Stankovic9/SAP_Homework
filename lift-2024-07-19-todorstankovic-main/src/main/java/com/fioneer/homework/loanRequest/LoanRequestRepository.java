package com.fioneer.homework.loanRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing LoanRequest entities.
 */
@Repository
public interface LoanRequestRepository extends JpaRepository<LoanRequest, Long> {
}
