package com.rupesh.assignment.bankapi.bankapi.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;



/**
 * The transaction DTO represents the Transaction entity
 * @Data
 * @author Rupesh
 *
 */
@Data
public class TransactionDTO {
  
  private String transactionID;
  private BigDecimal amount;
  private LocalDateTime date;

}
