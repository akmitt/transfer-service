package com.bank;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.AccountDetails;
import com.bank.dto.TransactionDetails;
import com.bank.dto.TransactionOutput;
import com.bank.entities.Account;
import com.bank.exception.AccountException;

import com.bank.exception.TransactionException;
import com.bank.exception.ValidationException;
import com.bank.service.AccountServiceImpl;
import com.bank.service.TransactionServiceImpl;

import io.swagger.annotations.ApiOperation;

/**
 * @author Akhil This is the controller which handles all the incoming requests
 */
@RestController

public class BankController {

	@Autowired
	AccountServiceImpl accountService;
	@Autowired
	TransactionServiceImpl transactionService;

	/**
	 * This is the rest end point method to create a account
	 * 
	 * @param accountDetails
	 * @param errors
	 * @return
	 * @throws ValidationException
	 * @throws AccountException
	 */
	@ApiOperation(value = "To  create new account with minimum balance")
	@PostMapping("/createAccount")
	public String createAccount(@Valid @RequestBody AccountDetails accountDetails, Errors errors)
			throws ValidationException, AccountException {
		/**
		 * Check if any field validation has occurred
		 */
		if (errors.hasErrors()) {
			StringBuffer errorField = new StringBuffer();
			for (FieldError e : errors.getFieldErrors()) {
				errorField.append(Constants.EMPTY_STRING+e.getField() + Constants.EMPTY_STRING + e.getDefaultMessage()+Constants.EMPTY_STRING);

			}
			throw new ValidationException(errorField.toString());
		}
		/**
		 * Calling method to create a account in database
		 */
		Account account = accountService.createAccount(accountDetails);
		return "Account No " + account.getAccountNumber() + " created Successfully with balance " + account.getBalance();

	}

	/**
	 * This method is used to make a transaction between debit and credit
	 * account
	 * 
	 * @param transactionDetails
	 * @param errors
	 * @return
	 * @throws TransactionException
	 * @throws ValidationException
	 */
	@ApiOperation(value = "To make a transefer from debit to credit account")
	@PostMapping("/transferMoney")
	public ResponseEntity<TransactionOutput> transferMoney(@Valid @RequestBody TransactionDetails transactionDetails,
			Errors errors) throws TransactionException, ValidationException {
		/**
		 * Check if any field validation has occurred
		 */
		if (errors.hasErrors()) {
			StringBuffer errorField = new StringBuffer();
			for (FieldError e : errors.getFieldErrors()) {
				errorField.append(Constants.EMPTY_STRING+e.getField() + Constants.EMPTY_STRING + e.getDefaultMessage() + Constants.EMPTY_STRING);

			}
			throw new ValidationException(errorField.toString());
		}
		/**
		 * Calling method to make a transaction
		 */
		TransactionOutput output = transactionService.makeTransaction(transactionDetails);
		return new ResponseEntity<>(output, HttpStatus.OK);

	}

}
