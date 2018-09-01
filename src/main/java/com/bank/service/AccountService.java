package com.bank.service;

import com.bank.dto.AccountDetails;
import com.bank.entities.Account;

public interface AccountService {
	
	Account createAccount(AccountDetails account);
	

}
