package com.rupesh.assignment.bankapi.bankapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.rupesh.assignment.bankapi.bankapi.domain.Account;
import com.rupesh.assignment.bankapi.bankapi.domain.Transaction;
import com.rupesh.assignment.bankapi.bankapi.exception.BankAPIException;
import com.rupesh.assignment.bankapi.bankapi.repository.AccountRepository;
import com.rupesh.assignment.bankapi.bankapi.repository.TransactionRepository;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private Account account;
    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        account = new Account();
        account.setId(1L);
        account.setBalance(BigDecimal.ZERO);

        transaction = new Transaction();
        transaction.setTransactionID("TS-12345");
        transaction.setAmount(new BigDecimal("100.00"));
        transaction.setDate(LocalDateTime.now());
        transaction.setAccount(account);
    }

    @Test
    void testCreateTransaction_AccountExists() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.save(account)).thenReturn(account);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction result = transactionService.createTransaction(1L, new BigDecimal("100.00"));

        assertNotNull(result);
        assertEquals(new BigDecimal("100.00"), result.getAmount());
        assertEquals("TS-12345", result.getTransactionID());
        assertEquals(account, result.getAccount());
        verify(accountRepository, times(1)).findById(1L);
        verify(accountRepository, times(1)).save(account);
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    void testCreateTransaction_AccountNotFound() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BankAPIException.class, () -> transactionService.createTransaction(1L, new BigDecimal("100.00")));
        verify(accountRepository, times(1)).findById(1L);
        verify(accountRepository, never()).save(account);
        verify(transactionRepository, never()).save(any(Transaction.class));
    }
}
