package com.rupesh.assignment.bankapi.bankapi.service;

import java.math.BigDecimal;
import com.rupesh.assignment.bankapi.bankapi.domain.Transaction;

/**
 * TransactionService uses create Transaction for creation of the transaction which will accept the
 * accountID and initial credit amount
 * 
 * @author Rupesh
 *
 */

public interface TransactionService {

  Transaction createTransaction(Long accountId, BigDecimal initialCredit);

}
