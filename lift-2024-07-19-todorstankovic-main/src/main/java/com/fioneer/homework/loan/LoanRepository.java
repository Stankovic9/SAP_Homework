package com.fioneer.homework.loan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing LoanType entities.
 */
@Repository
public interface LoanRepository extends JpaRepository<LoanType,Long> {

    /**
     * Finds a LoanType entity by its name.
     *
     * @param name the name of the LoanType to find.
     * @return an Optional containing the found LoanType, or an empty Optional if no LoanType with the given name exists.
     */
    Optional<LoanType> findByName(String name);
}
