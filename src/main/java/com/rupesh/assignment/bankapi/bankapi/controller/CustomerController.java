package com.rupesh.assignment.bankapi.bankapi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rupesh.assignment.bankapi.bankapi.domain.CustomerDTO;
import com.rupesh.assignment.bankapi.bankapi.service.CustomerService;

/**
 * REST controller for managing customers.
 */

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  private final CustomerService customerService;

  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping("/{customerID}")
  public ResponseEntity<CustomerDTO> getCustomerInfo(@PathVariable String customerID) {
    return ResponseEntity.ok(customerService.getCustomerInfo(customerID));
  }

  @GetMapping("/all")
  public ResponseEntity<List<CustomerDTO>> getAllAccounts() {
    return ResponseEntity.ok(customerService.getAllCustomers());
  }

}
