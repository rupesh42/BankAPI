package com.rupesh.assignment.bankapi.bankapi.domain;

import java.math.BigDecimal;
import lombok.Data;

/**
 * The AccountRequest is to handle the requests to create new Current Account
 */

@Data
public class AccountRequest {
  private String customerID;
  private BigDecimal initialCredit;

}
