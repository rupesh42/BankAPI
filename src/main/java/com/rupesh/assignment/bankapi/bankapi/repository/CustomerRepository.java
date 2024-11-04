package com.rupesh.assignment.bankapi.bankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rupesh.assignment.bankapi.bankapi.domain.Customer;

/**
 * CustomerRepository is to handle the customer related operations.
 *
 * @author Rupesh
 *
 */


@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

}
