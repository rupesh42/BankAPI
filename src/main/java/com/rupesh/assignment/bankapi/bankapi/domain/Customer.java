package com.rupesh.assignment.bankapi.bankapi.domain;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;



/**
 * The Customer entity is to handle the Custoemr entity, customer ID as primary key and name,
 * surname of the customer.
 * 
 * @Data
 * @author Rupesh
 *
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Customer {

  @Id
  private String customerId;
  private String name;
  private String surname;
  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Account> accounts;

}
