package com.bank.exception;

/**This is the exception class which handles the exceptions
 * @author Akhil
 *
 */
public class ValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ValidationException(String message) {
		super(message);
	}
	
}
