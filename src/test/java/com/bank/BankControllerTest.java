package com.bank;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.bank.dto.AccountDetails;
import com.bank.dto.TransactionDetails;

/**This is the integration test to make sure all the endpoints exposed are responding
 * @author Akhil
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BankControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	HttpHeaders headers = new HttpHeaders();

	/**
	 * This  method check endpoint for Add Account
	 */
	@Test
	public void addAccount() {

		AccountDetails account = new AccountDetails();
		account.setAccountHolderName("Akhil");
		account.setBalance(0);

		HttpEntity<AccountDetails> entity = new HttpEntity<AccountDetails>(account, headers);

		ResponseEntity<String> response = this.restTemplate.exchange("http://localhost:" + port + "/createAccount",
				HttpMethod.POST, entity, String.class);

		assertTrue(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));

	}

	/** This method test endppint for making transaction
	 * 
	 */
	@Test
	public void makeTransaction() {

		TransactionDetails transactionDetails = new TransactionDetails();
		transactionDetails.setCreditAccount(0);

		HttpEntity<TransactionDetails> entity = new HttpEntity<TransactionDetails>(transactionDetails, headers);

		ResponseEntity<String> response = this.restTemplate.exchange("http://localhost:" + port + "/transferMoney",
				HttpMethod.POST, entity, String.class);

		assertTrue(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));

	}

	
}
