package com.rupesh.assignment.bankapi.bankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rupesh.assignment.bankapi.bankapi.domain.Account;

/**
 * Account Repository is to handle the account related operations.
 *
 * @author Rupesh
 *
 */

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
  
  long countByCustomer_CustomerId(String customerID);
}
