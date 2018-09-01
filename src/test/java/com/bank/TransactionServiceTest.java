package com.bank;

import static org.junit.Assert.*;

import java.util.Optional;

import org.assertj.core.api.Assertions;
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
import com.bank.dto.TransactionDetails;
import com.bank.dto.TransactionOutput;
import com.bank.entities.Account;
import com.bank.exception.AccountException;
import com.bank.exception.TransactionException;
import com.bank.service.TransactionServiceImpl;

/**
 * This is the test class for transaction service
 * 
 * @author Akhil
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {

	@TestConfiguration
	static class TransactionServiceTestContextConfiguration {

		@Bean
		public TransactionServiceImpl transactionService() {
			return new TransactionServiceImpl();
		}
	}

	@Autowired
	private TransactionServiceImpl transactionService;

	@MockBean
	private AccountRepository accountRepo;

	@Before
	public void setUp() {
		Account accountDebit = new Account();
		accountDebit.setName("Akhil");
		accountDebit.setBalance(100);
		accountDebit.setAccountNumber(1000);

		Account accountCredit = new Account();
		accountCredit.setName("Akhil");
		accountCredit.setBalance(200);
		accountCredit.setAccountNumber(1001);

		Mockito.when(accountRepo.findById(1000)).thenReturn(Optional.of(accountDebit));
		Mockito.when(accountRepo.findById(1001)).thenReturn(Optional.of(accountCredit));
		Mockito.when(accountRepo.save(accountCredit)).thenReturn(accountCredit);
	}

	/**
	 * test if credit account is present or not
	 * 
	 */
	@Test
	public void checkCreditAccountTest() {

		Optional<Account> account = transactionService.checkCreditAccount(1000);
		assertTrue(account.isPresent());

	}

	/**
	 * test method to update debit account
	 * 
	 */
	@Test
	public void updateDebitTest() {
		Account accountDebit = new Account();
		accountDebit.setName("Akhil");
		accountDebit.setBalance(200);
		accountDebit.setAccountNumber(1001);

		transactionService.updateDebitAccount(accountDebit);

	}

	/**
	 * test method to update credit account
	 *
	 */
	@Test
	public void updateCreditTest() {
		Account accountCredit = new Account();
		accountCredit.setName("Akhil");
		accountCredit.setBalance(200);
		accountCredit.setAccountNumber(1001);

		transactionService.updateCreditAccount(accountCredit);

	}

	/**
	 * test method when debit account dont exist
	 */
	@Test
	public void makeTransactionTestDebitAccountInvalid() {
		TransactionDetails transactionDetails = new TransactionDetails();
		transactionDetails.setDebitAccount(456);
		try {
			transactionService.makeTransaction(transactionDetails);
			fail();
		} catch (Exception ex) {
			Assertions.assertThat(ex.getMessage()).isEqualTo(Constants.DEBIT_INVALID);
		}

	}

	/**
	 * test method when debit account has less balance
	 */
	@Test
	public void makeTransactionTestDebitAccountInvalidInsufficientBalance() {
		TransactionDetails transactionDetails = new TransactionDetails();
		transactionDetails.setDebitAccount(1000);
		transactionDetails.setTransferAmount(5000);
		try {
			transactionService.makeTransaction(transactionDetails);
			fail();
		} catch (Exception ex) {
			Assertions.assertThat(ex.getMessage()).isEqualTo(Constants.INSUFFICIENT_BALANCE);
		}

	}

	/**
	 * test method when credit account is invalid
	 */
	@Test
	public void makeTransactionTestCreditInvalid() {
		TransactionDetails transactionDetails = new TransactionDetails();
		transactionDetails.setDebitAccount(1000);
		transactionDetails.setTransferAmount(50);
		transactionDetails.setCreditAccount(4597);
		try {
			transactionService.makeTransaction(transactionDetails);
			fail();
		} catch (Exception ex) {
			Assertions.assertThat(ex.getMessage()).isEqualTo(Constants.CREDIT_INVALID);
		}

	}

	/**
	 * test method to make valid transaction
	 * 
	 * @throws TransactionException
	 */
	@Test
	public void makeTransactionTest() throws TransactionException {
		TransactionDetails transactionDetails = new TransactionDetails();
		transactionDetails.setDebitAccount(1000);
		transactionDetails.setTransferAmount(50);
		transactionDetails.setCreditAccount(1001);

		TransactionOutput output = transactionService.makeTransaction(transactionDetails);

		assertNotNull(output);
		assertEquals(Constants.SUCCESS_CREDIT, output.getMessage());
	}

	@Test
	public void checkDebitAccountTest() throws AccountException {

		Optional<Account> account = transactionService.checkDebitAccount(1001);
		assertTrue(account.isPresent());

	}
}