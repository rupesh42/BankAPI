package com.rupesh.assignment.bankapi.bankapi.service;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rupesh.assignment.bankapi.bankapi.domain.Account;
import com.rupesh.assignment.bankapi.bankapi.domain.AccountDTO;
import com.rupesh.assignment.bankapi.bankapi.domain.Customer;
import com.rupesh.assignment.bankapi.bankapi.domain.Transaction;
import com.rupesh.assignment.bankapi.bankapi.domain.TransactionDTO;
import com.rupesh.assignment.bankapi.bankapi.exception.BankAPIException;
import com.rupesh.assignment.bankapi.bankapi.repository.AccountRepository;
import com.rupesh.assignment.bankapi.bankapi.repository.CustomerRepository;
import com.rupesh.assignment.bankapi.bankapi.utils.BankAPIConstants;

/**
 * This is an implementation class of AccountService.
 * @author Rupesh
 *
 */

@Service
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;

  private final CustomerRepository customerRepository;

  private final TransactionService transactionService;

  @Autowired
  public AccountServiceImpl(AccountRepository accountRepository,
      CustomerRepository customerRepository, TransactionService transactionService) {
    this.accountRepository = accountRepository;
    this.customerRepository = customerRepository;
    this.transactionService = transactionService;
  }

  @Override
  public Account createAccount(String customerID, BigDecimal initialCredit) {
    Customer customer = customerRepository.findById(customerID)
        .orElseThrow(() -> new BankAPIException(BankAPIConstants.CUSTOMER_FOT_FOUND_ERROR));

    String accountId =
        customerID + "-" + (accountRepository.countByCustomer_CustomerId(customerID) + 1);

    Account newAccount = new Account();
    newAccount.setCustomer(customer);
    newAccount.setAccountId(accountId);
    newAccount.setBalance(BigDecimal.ZERO);
    Account savedAccount = accountRepository.save(newAccount);
    if (initialCredit.compareTo(BigDecimal.ZERO) != 0) {
      transactionService.createTransaction(savedAccount.getId(), initialCredit);
    }
    return savedAccount;
  }

  @Override
  public AccountDTO getAccount(Long accountId) {
    Account account = accountRepository.findById(accountId)
        .orElseThrow(() -> new BankAPIException(BankAPIConstants.ACCOUNT_NOT_FOUND_ERROR));

    return convertToDTO(account);
  }

  @Override
  public List<AccountDTO> getAllAccounts() {
    List<Account> accounts = accountRepository.findAll();
    return accounts.stream().map(this::convertToDTO).toList();
  }

  private AccountDTO convertToDTO(Account account) {
    AccountDTO accountDTO = new AccountDTO();
    accountDTO.setId(account.getId());
    accountDTO.setAccountId(account.getAccountId());
    accountDTO.setBalance(account.getBalance());
    accountDTO.setTransactions(
        account.getTransactions().stream().map(this::convertTransactionToDTO).toList());
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
