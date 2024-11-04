package com.rupesh.assignment.bankapi.bankapi.exception;

/**
 * This class will be used to throw custom made exception in cases of error
 * 
 * @author Rupesh
 *
 */
public class BankAPIException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public BankAPIException(String message) {
    super(message);
  }

}
