package com.bank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bank.Constants;
import com.bank.db.AccountRepository;
import com.bank.dto.TransactionDetails;
import com.bank.dto.TransactionOutput;
import com.bank.entities.Account;
import com.bank.exception.TransactionException;

@Component
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	@Transactional
	public synchronized TransactionOutput makeTransaction(TransactionDetails transactionDetails) throws TransactionException {

		TransactionOutput output= new TransactionOutput() ;
		Optional<Account> debitAccount = checkDebitAccount(transactionDetails.getDebitAccount());
		if (!debitAccount.isPresent()) {
			throw new TransactionException(Constants.DEBIT_INVALID);
		} else if (debitAccount.get().getBalance() < transactionDetails.getTransferAmount()) {
			throw new TransactionException(Constants.INSUFFICIENT_BALANCE);
		}

		Optional<Account> creditAccount = checkCreditAccount(transactionDetails.getCreditAccount());

		if (!creditAccount.isPresent()) {
			throw new TransactionException(Constants.CREDIT_INVALID);
		} else {

			Account debit = debitAccount.get();
			debit.setBalance(debit.getBalance() - transactionDetails.getTransferAmount());
			updateDebitAccount(debit);
			output.setDebitAccount(debit);
			Account credit = creditAccount.get();
			credit.setBalance(credit.getBalance() + transactionDetails.getTransferAmount());
			updateCreditAccount(credit);
		}
		output.setMessage(Constants.SUCCESS_CREDIT);
		return output;
	}

	@Override
	public Optional<Account> checkDebitAccount(int account) {
		Optional<Account> debitAccount = accountRepository.findById(account);
		return debitAccount;
	}

	@Override
	public Optional<Account> checkCreditAccount(int account) {
		Optional<Account> creditAccount = accountRepository.findById(account);
		return creditAccount;
	}

	@Override
	public void updateDebitAccount(Account account) {
		accountRepository.save(account);

	}

	@Override
	public void updateCreditAccount(Account account) {
		accountRepository.save(account);

	}

}
