package com.bank.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * This is the bean for account details
 * 
 * @author Akhil
 *
 */
public class TransactionDetails {

	@Min(1)
	private Integer debitAccount;
	@Min(1)
	private Integer creditAccount;
	@Min(1)
	private Integer transferAmount;
	
	public Integer getDebitAccount() {
		return debitAccount;
	}
	public void setDebitAccount(Integer debitAccount) {
		this.debitAccount = debitAccount;
	}
	public Integer getCreditAccount() {
		return creditAccount;
	}
	public void setCreditAccount(Integer creditAccount) {
		this.creditAccount = creditAccount;
	}
	public Integer getTransferAmount() {
		return transferAmount;
	}
	public void setTransferAmount(Integer transferAmount) {
		this.transferAmount = transferAmount;
	}
	

	
}
