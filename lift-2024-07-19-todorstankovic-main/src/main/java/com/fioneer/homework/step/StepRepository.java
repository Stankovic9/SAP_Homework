package com.fioneer.homework.step;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing Step entities.
 */
@Repository
public interface StepRepository extends JpaRepository<Step, Long> {

    /**
     * Finds a Step entity by its order number.
     *
     * @param orderNumber the order number of the Step to find.
     * @return an Optional containing the found Step, or an empty Optional if no Step with the given order number exists.
     */
    Optional<Step> findByOrderNumber(Integer orderNumber);

    /**
     * Finds a Step entity by its ID.
     *
     * @param id the ID of the Step to find.
     * @return an Optional containing the found Step, or an empty Optional if no Step with the given ID exists.
     */
    Optional<Step> findById(Long id);
}
