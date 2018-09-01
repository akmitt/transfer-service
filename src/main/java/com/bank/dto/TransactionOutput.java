package com.bank.dto;

import com.bank.entities.Account;

/** This is the  output bean for making a transaction
 * @author Akhil
 *
 */
public class TransactionOutput {
	
    private Account debitAccount;
    private String message;
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the debitAccount
	 */
	public Account getDebitAccount() {
		return debitAccount;
	}
	/**
	 * @param debitAccount the debitAccount to set
	 */
	public void setDebitAccount(Account debitAccount) {
		this.debitAccount = debitAccount;
	}
	
}
