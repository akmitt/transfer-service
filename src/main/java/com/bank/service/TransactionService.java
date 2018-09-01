package com.bank.service;

import java.util.Optional;

import com.bank.dto.TransactionDetails;
import com.bank.dto.TransactionOutput;
import com.bank.entities.Account;
import com.bank.exception.TransactionException;

public interface TransactionService {

	/**
	 * Check if Debit account exists or not and get it if it exists
	 * 
	 * @param account
	 * @return
	 */
	Optional<Account> checkDebitAccount(int account);

	/**
	 * Check if Credit Account exists or not and get details if if exists
	 * 
	 * @param account
	 * @return
	 */
	Optional<Account> checkCreditAccount(int account);

	/**
	 * Updates the Debit Account
	 * 
	 * @param account
	 */
	void updateDebitAccount(Account account);

	/**
	 * Updates the Credit Account
	 * 
	 * @param account
	 */
	void updateCreditAccount(Account account);

	/**
	 * This method makes the transaction
	 * 
	 * @param transactionDetails
	 * @return 
	 * @throws TransactionException 
	 */
	TransactionOutput makeTransaction(TransactionDetails transactionDetails) throws TransactionException;

}
