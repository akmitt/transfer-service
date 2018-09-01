package com.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bank.Constants;
import com.bank.db.AccountRepository;
import com.bank.dto.AccountDetails;
import com.bank.entities.Account;
import com.bank.exception.AccountException;

@Service
public class AccountServiceImpl {

	@Autowired
	private AccountRepository accountRepository;

	public AccountRepository getAccountRepository() {
		return accountRepository;
	}

	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	/**
	 * This method is used to create the account
	 * 
	 * @param accountDetails
	 * @return
	 * @throws BankException
	 */
	public Account createAccount(AccountDetails accountDetails) throws AccountException {
		
		Account account = new Account();
		account.setBalance(accountDetails.getBalance());
		account.setName(accountDetails.getAccountHolderName());
		Account createdAccount = null;
		try {
			createdAccount = accountRepository.save(account);
		} catch (Exception e) {
			throw new AccountException(Constants.ACCOUNt_ERROR);
		}

		return createdAccount;
	}

}
