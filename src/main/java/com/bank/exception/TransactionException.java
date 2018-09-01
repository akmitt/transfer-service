package com.bank.exception;

/**This is the exception class which handles the exceptions for transactions
 * @author Akhil
 *
 */
public class TransactionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TransactionException(String message) {
		super(message);
	}
	
}
