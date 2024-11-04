package com.rupesh.assignment.bankapi.bankapi.service;

import java.util.List;
import com.rupesh.assignment.bankapi.bankapi.domain.Customer;
import com.rupesh.assignment.bankapi.bankapi.domain.CustomerDTO;

/**
 * CustomerService is for handling Customer related operations such as creation, fetching the details
 * as per ID and fetching all the details irrespective to the ID
 * 
 * @author Rupesh
 *
 */

public interface CustomerService {
  
  CustomerDTO getCustomerInfo(String customerID);
  
  Customer addCustomer(Customer customer);
  
  void ensureCustomerExists(Customer customer);

  List<CustomerDTO> getAllCustomers();
}
