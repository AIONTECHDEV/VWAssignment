package com.example.digitalhub.vo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class AccountVO {
	
	private BigInteger account;
	private BigDecimal balance;
	private BigInteger owner;
	private Date createdAt;
	
	public BigInteger getAccount() {
		return account;
	}
	public void setAccount(BigInteger account) {
		this.account = account;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigInteger getOwner() {
		return owner;
	}
	public void setOwner(BigInteger owner) {
		this.owner = owner;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
}
