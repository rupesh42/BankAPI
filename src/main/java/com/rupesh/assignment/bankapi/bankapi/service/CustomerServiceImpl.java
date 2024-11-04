package com.rupesh.assignment.bankapi.bankapi.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rupesh.assignment.bankapi.bankapi.domain.Account;
import com.rupesh.assignment.bankapi.bankapi.domain.Customer;
import com.rupesh.assignment.bankapi.bankapi.domain.CustomerDTO;
import com.rupesh.assignment.bankapi.bankapi.domain.Transaction;
import com.rupesh.assignment.bankapi.bankapi.domain.TransactionDTO;
import com.rupesh.assignment.bankapi.bankapi.exception.BankAPIException;
import com.rupesh.assignment.bankapi.bankapi.repository.CustomerRepository;
import com.rupesh.assignment.bankapi.bankapi.utils.BankAPIConstants;

/**
 * This is an implementation class of Customer Service.
 * 
 * @author Rupesh
 *
 */

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  @Autowired
  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public CustomerDTO getCustomerInfo(String customerID) {

    return convertToDTO(customerRepository.findById(customerID)
        .orElseThrow(() -> new BankAPIException(BankAPIConstants.CUSTOMER_FOT_FOUND_ERROR)));
  }

  @Override
  public Customer addCustomer(Customer customer) {
    return customerRepository.save(customer);
  }

  @Override
  public void ensureCustomerExists(Customer customer) {
    if (!customerRepository.existsById(customer.getCustomerId())) {
      addCustomer(customer);
    }
  }

  @Override
  public List<CustomerDTO> getAllCustomers() {
    List<Customer> customer = customerRepository.findAll();
    return customer.stream().map(this::convertToDTO).toList();
  }

  private CustomerDTO convertToDTO(Customer customer) {
    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setName(customer.getName());
    customerDTO.setSurname(customer.getSurname());

    BigDecimal balance =
        customer.getAccounts() != null
            ? customer.getAccounts().stream().map(Account::getBalance).reduce(BigDecimal.ZERO,
                BigDecimal::add)
            : BigDecimal.ZERO;
    customerDTO.setBalance(balance);

    List<TransactionDTO> transactionDTOs;

    if (customer.getAccounts() != null) {
      transactionDTOs = customer.getAccounts().stream().flatMap(account -> {
        if (account.getTransactions() != null) {
          return account.getTransactions().stream();
        } else {
          return Stream.empty();
        }
      }).map(this::convertTransactionToDTO).toList();
    } else {
      transactionDTOs = Collections.emptyList();
    }

    customerDTO.setTransactions(transactionDTOs);
    return customerDTO;
  }


  private TransactionDTO convertTransactionToDTO(Transaction transaction) {
    TransactionDTO transactionDTO = new TransactionDTO();
    transactionDTO.setTransactionID(transaction.getTransactionID());
    transactionDTO.setAmount(transaction.getAmount());
    transactionDTO.setDate(transaction.getDate());
    return transactionDTO;
  }
}

