package com.rupesh.assignment.bankapi.bankapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.rupesh.assignment.bankapi.bankapi.domain.Customer;
import com.rupesh.assignment.bankapi.bankapi.exception.BankAPIException;

/**
 * This class loads the dtaa into Customer automatically as the application is ready.
 * You may add more customer using setters in Customer entity.
 * @author Rupesh
 *
 */
@Component
public class DataLoader implements CommandLineRunner {

  private CustomerService customerService;

  @Autowired
  public DataLoader(CustomerService customerService) {
    this.customerService = customerService;
  }

  @Override
  public void run(String... args) throws BankAPIException {
    Customer customer1 = new Customer();
    customer1.setCustomerId("535");
    customer1.setName("Rupesh");
    customer1.setSurname("SA");

    Customer customer2 = new Customer();
    customer2.setCustomerId("213");
    customer2.setName("Ajan");
    customer2.setSurname("BA");

    customerService.ensureCustomerExists(customer1);
    customerService.ensureCustomerExists(customer2);
  }
}
