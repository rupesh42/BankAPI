package com.rupesh.assignment.bankapi.bankapi.domain;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

/**
 * The DTO is to represent Account Entity
 * @author Rupesh
 *
 */
@Data
public class AccountDTO {

  private Long id;
  private String accountId;
  private BigDecimal balance;
  private List<TransactionDTO> transactions;

}
