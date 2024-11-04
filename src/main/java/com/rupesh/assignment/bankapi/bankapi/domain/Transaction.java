package com.rupesh.assignment.bankapi.bankapi.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/** The transaction entity is to store all the transactions of existing customer. 
 *  store id as primary key and transaction Key is auto-generated.
 * @author Rupesh
 *
 */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private String transactionID;
  
  private BigDecimal amount;
  
  private LocalDateTime date;
  
  @ManyToOne
  @JoinColumn(name = "account_id")
  
  private Account account;

}
