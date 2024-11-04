package com.rupesh.assignment.bankapi.bankapi.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rupesh.assignment.bankapi.bankapi.domain.Account;
import com.rupesh.assignment.bankapi.bankapi.domain.AccountDTO;
import com.rupesh.assignment.bankapi.bankapi.domain.AccountRequest;
import com.rupesh.assignment.bankapi.bankapi.domain.Transaction;
import com.rupesh.assignment.bankapi.bankapi.domain.TransactionDTO;
import com.rupesh.assignment.bankapi.bankapi.service.AccountService;
import com.rupesh.assignment.bankapi.bankapi.utils.BankAPIConstants;

/**
 * REST controller for managing accounts.
 */

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

  private final AccountService accountService;

  @Autowired
  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @PostMapping("/create")
  public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountRequest request) {
    Account newAccount =
        accountService.createAccount(request.getCustomerID(), request.getInitialCredit());
    return ResponseEntity.ok(convertToDTO(newAccount));
  }

  @GetMapping("/{accountId}")
  public ResponseEntity<AccountDTO> getAccount(@PathVariable Long accountId) {
    return ResponseEntity.ok(accountService.getAccount(accountId));
  }

  @GetMapping("/all")
  public ResponseEntity<List<AccountDTO>> getAllAccounts() {
    List<AccountDTO> accounts = accountService.getAllAccounts();
    accounts.forEach(this::formatTransactionDates);
    return ResponseEntity.ok(accounts);
  }

  private void formatTransactionDates(AccountDTO accountDTO) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(BankAPIConstants.PATTERN);
    accountDTO.getTransactions().forEach(transaction -> {
      transaction.setDate(LocalDateTime.parse(transaction.getDate().format(formatter), formatter));
    });
  }

  private AccountDTO convertToDTO(Account account) {
    AccountDTO accountDTO = new AccountDTO();
    accountDTO.setId(account.getId());
    accountDTO.setAccountId(account.getAccountId());
    accountDTO.setBalance(account.getBalance());
    accountDTO.setTransactions(account.getTransactions().stream().map(this::convertTransactionToDTO)
        .toList());
    return accountDTO;
  }

  private TransactionDTO convertTransactionToDTO(Transaction transaction) {
    TransactionDTO transactionDTO = new TransactionDTO();
    transactionDTO.setTransactionID(transaction.getTransactionID());
    transactionDTO.setAmount(transaction.getAmount());
    transactionDTO.setDate(transaction.getDate());
    return transactionDTO;
  }
}
