package com.rupesh.assignment.bankapi.bankapi.service;

import java.math.BigDecimal;
import java.util.List;
import com.rupesh.assignment.bankapi.bankapi.domain.Account;
import com.rupesh.assignment.bankapi.bankapi.domain.AccountDTO;

/**
 * AccountService is for handling Account related operations such as creation, fetching the details
 * as per ID and fetching all the details irrespective to the ID
 * 
 * @author Rupesh
 *
 */

public interface AccountService {

  Account createAccount(String customerID, BigDecimal bigDecimal);

  AccountDTO getAccount(Long accountId);

  List<AccountDTO> getAllAccounts();
}
