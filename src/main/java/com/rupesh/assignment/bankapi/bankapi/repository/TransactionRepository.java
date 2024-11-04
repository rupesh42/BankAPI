package com.rupesh.assignment.bankapi.bankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rupesh.assignment.bankapi.bankapi.domain.Transaction;

/**
 * TransactionRepository is to handle the transaction related operations.
 *
 * @author Rupesh
 *
 */

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
