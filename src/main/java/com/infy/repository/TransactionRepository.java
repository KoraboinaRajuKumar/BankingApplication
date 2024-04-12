package com.infy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	
	// newest records in first
	List<Transaction> findByAccountIdOrderByTimestampDesc(Long accountId);

}
