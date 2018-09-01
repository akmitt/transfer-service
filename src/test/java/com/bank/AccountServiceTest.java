package com.bank;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.bank.db.AccountRepository;
import com.bank.dto.AccountDetails;
import com.bank.entities.Account;
import com.bank.exception.AccountException;
import com.bank.service.AccountServiceImpl;

/** This is the test class for account service
 * @author Akhil
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

	@TestConfiguration
	static class AccountServiceTestContextConfiguration {

		@Bean
		public AccountServiceImpl accountService() {
			return new AccountServiceImpl();
		}
	}

	@Autowired
	private AccountServiceImpl accountService;

	@MockBean
	private AccountRepository accountRepo;

	@Before
	public void setUp() {
		Account account = new Account();
		account.setName("Akhil");
		account.setBalance(100);
		Mockito.when(accountRepo.save(account)).thenReturn(account);
	}

	/** Test method to create account
	 * @throws AccountException
	 */
	@Test
	public void createAccount() throws AccountException {
		AccountDetails account = new AccountDetails();
		account.setAccountHolderName("Akhil");
		account.setBalance(100);
		accountService.createAccount(account);

	}

}