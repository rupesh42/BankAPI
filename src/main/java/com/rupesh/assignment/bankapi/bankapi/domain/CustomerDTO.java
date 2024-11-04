package com.rupesh.assignment.bankapi.bankapi.domain;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

/**
 * The CustomerDTO is to represent the Customer entity,
 * @author Rupesh
 *
 */

@Data
public class CustomerDTO {

  private String name;
  private String surname;
  private BigDecimal balance;
  private List<TransactionDTO> transactions;

}
