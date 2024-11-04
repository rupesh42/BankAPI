package com.rupesh.assignment.bankapi.bankapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.rupesh.assignment.bankapi.bankapi.domain.Account;
import com.rupesh.assignment.bankapi.bankapi.domain.AccountDTO;
import com.rupesh.assignment.bankapi.bankapi.domain.Customer;
import com.rupesh.assignment.bankapi.bankapi.exception.BankAPIException;
import com.rupesh.assignment.bankapi.bankapi.repository.AccountRepository;
import com.rupesh.assignment.bankapi.bankapi.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private AccountServiceImpl accountService;

    private Customer customer;
    private Account account;
    private AccountDTO accountDTO;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customer.setCustomerId("12345");

        account = new Account();
        account.setId(1L);
        account.setCustomer(customer);
        account.setAccountId("12345-1");
        account.setBalance(BigDecimal.ZERO);

        accountDTO = new AccountDTO();
        accountDTO.setAccountId("12345-1");
        // other properties...
    }

    @Test
    void testCreateAccount_CustomerExists() {
        when(customerRepository.findById("12345")).thenReturn(Optional.of(customer));
        when(accountRepository.countByCustomer_CustomerId("12345")).thenReturn(0L);
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account result = accountService.createAccount("12345", new BigDecimal("100.00"));

        assertNotNull(result);
        verify(customerRepository, times(1)).findById("12345");
        verify(accountRepository, times(1)).countByCustomer_CustomerId("12345");
        verify(accountRepository, times(1)).save(any(Account.class));
        verify(transactionService, times(1)).createTransaction(account.getId(), new BigDecimal("100.00"));
    }

    @Test
    void testCreateAccount_CustomerNotFound() {
        when(customerRepository.findById("12345")).thenReturn(Optional.empty());

        assertThrows(BankAPIException.class, () -> accountService.createAccount("12345", new BigDecimal("100.00")));
        verify(customerRepository, times(1)).findById("12345");
        verify(accountRepository, never()).countByCustomer_CustomerId("12345");
        verify(accountRepository, never()).save(any(Account.class));
        verify(transactionService, never()).createTransaction(anyLong(), any(BigDecimal.class));
    }

    @Test
    void testGetAccount_AccountExists() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        AccountDTO result = accountService.getAccount(1L);

        assertNotNull(result);
        assertEquals("12345-1", result.getAccountId());
        verify(accountRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAccount_AccountNotFound() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BankAPIException.class, () -> accountService.getAccount(1L));
        verify(accountRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllAccounts() {
        when(accountRepository.findAll()).thenReturn(List.of(account));

        List<AccountDTO> result = accountService.getAllAccounts();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("12345-1", result.get(0).getAccountId());
        verify(accountRepository, times(1)).findAll();
    }
}
