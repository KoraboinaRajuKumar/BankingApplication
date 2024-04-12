package com.infy.dto;

import java.time.LocalDateTime;

public class TrasactionDto {
	
	private Integer id;
	private Long accountId;
	private String transactionType;
	private double amount;
	private LocalDateTime timestamp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", accountId=" + accountId + ", transactionType=" + transactionType
				+ ", amount=" + amount + ", timestamp=" + timestamp + "]";
	}

	public TrasactionDto(Integer id, Long accountId, String transactionType, double amount, LocalDateTime timestamp) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.transactionType = transactionType;
		this.amount = amount;
		this.timestamp = timestamp;
	}
}