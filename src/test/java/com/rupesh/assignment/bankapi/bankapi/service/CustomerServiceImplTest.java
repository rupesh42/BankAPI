package com.rupesh.assignment.bankapi.bankapi.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.rupesh.assignment.bankapi.bankapi.domain.Customer;
import com.rupesh.assignment.bankapi.bankapi.domain.CustomerDTO;
import com.rupesh.assignment.bankapi.bankapi.exception.BankAPIException;
import com.rupesh.assignment.bankapi.bankapi.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

  @Mock
  private CustomerRepository customerRepository;

  @InjectMocks
  private CustomerServiceImpl customerService;

  private Customer customer;

  @BeforeEach
  public void setUp() {
    customer = new Customer();
    customer.setCustomerId("12345");
  }

  @Test
    void testGetCustomerInfo_CustomerExists() {
        when(customerRepository.findById("12345")).thenReturn(Optional.of(customer));

        CustomerDTO result = customerService.getCustomerInfo("12345");

        assertNotNull(result);
        verify(customerRepository, times(1)).findById("12345");
    }

  @Test
    void testGetCustomerInfo_CustomerNotFound() {
        when(customerRepository.findById("12345")).thenReturn(Optional.empty());

        assertThrows(BankAPIException.class, () -> customerService.getCustomerInfo("12345"));
        verify(customerRepository, times(1)).findById("12345");
    }

  @Test
    void testAddCustomer() {
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer result = customerService.addCustomer(customer);

        assertNotNull(result);
        verify(customerRepository, times(1)).save(customer);
    }

  @Test
    void testEnsureCustomerExists_CustomerNotExists() {
        when(customerRepository.existsById("12345")).thenReturn(false);
        when(customerRepository.save(customer)).thenReturn(customer);

        customerService.ensureCustomerExists(customer);

        verify(customerRepository, times(1)).existsById("12345");
        verify(customerRepository, times(1)).save(customer);
    }

  @Test
    void testEnsureCustomerExists_CustomerExists() {
        when(customerRepository.existsById("12345")).thenReturn(true);

        customerService.ensureCustomerExists(customer);

        verify(customerRepository, times(1)).existsById("12345");
        verify(customerRepository, never()).save(customer);
    }
}
