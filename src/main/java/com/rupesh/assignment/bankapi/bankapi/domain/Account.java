package com.rupesh.assignment.bankapi.bankapi.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * This entity is for Account to store the ID as primary key and accountID, balance, and transations 
 * @author Rupesh
 *
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id"})
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private String accountId;
  
  private BigDecimal balance;
  
  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;
  
  @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY) 
  private List<Transaction> transactions = new ArrayList<>();

}
