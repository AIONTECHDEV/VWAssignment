package com.example.digitalhub.vo.request;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Transaction Request Model")
public class TransferRequestVO {
	
	@ApiModelProperty(value = "Account where quantity will be discounted", required=true)
	private BigInteger fromAccount;
	@ApiModelProperty(value = "Account where quantity will be applied", required=true)
	private BigInteger toAccount;
	@ApiModelProperty(value = "The amount", required=true)
	private BigDecimal amount;
	private Date sentAt;
	public BigInteger getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(BigInteger fromAccount) {
		this.fromAccount = fromAccount;
	}
	public BigInteger getToAccount() {
		return toAccount;
	}
	public void setToAccount(BigInteger toAccount) {
		this.toAccount = toAccount;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getSentAt() {
		return sentAt;
	}
	public void setSentAt(Date sentAt) {
		this.sentAt = sentAt;
	}

}
