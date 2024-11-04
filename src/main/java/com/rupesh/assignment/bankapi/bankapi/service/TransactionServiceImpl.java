package com.rupesh.assignment.bankapi.bankapi.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rupesh.assignment.bankapi.bankapi.domain.Account;
import com.rupesh.assignment.bankapi.bankapi.domain.Transaction;
import com.rupesh.assignment.bankapi.bankapi.exception.BankAPIException;
import com.rupesh.assignment.bankapi.bankapi.repository.AccountRepository;
import com.rupesh.assignment.bankapi.bankapi.repository.TransactionRepository;
import com.rupesh.assignment.bankapi.bankapi.utils.BankAPIConstants;

/**
 * This is to handle the operations related to Transaction and is an implementation of
 * TransactionService
 * 
 * @author Rupesh
 *
 */

@Service
public class TransactionServiceImpl implements TransactionService {

  private final TransactionRepository transactionRepository;

  private final AccountRepository accountRepository;

  @Autowired
  public TransactionServiceImpl(TransactionRepository transactionRepository,
      AccountRepository accountRepository) {
    this.transactionRepository = transactionRepository;
    this.accountRepository = accountRepository;
  }

  @Override
  public Transaction createTransaction(Long accountId, BigDecimal amount) {
    Account account = accountRepository.findById(accountId)
        .orElseThrow(() -> new BankAPIException(BankAPIConstants.ACCOUNT_NOT_FOUND_ERROR));
    Transaction transaction = new Transaction();
    transaction.setTransactionID("TS-" + generateNumericUUID());
    transaction.setAmount(amount);
    transaction.setDate(LocalDateTime.now());
    transaction.setAccount(account);

    account.setBalance(account.getBalance().add(amount));
    accountRepository.save(account);

    return transactionRepository.save(transaction);
  }

  private String generateNumericUUID() {
    LocalDate date = LocalDate.now();
    String uuid =
        date.toString().replace("-", "") + UUID.randomUUID().toString().replaceAll("\\D", "");
    return uuid.length() > 10 ? uuid.substring(0, 15)
        : String.format("%015d", Long.parseLong(uuid));
  }
}
