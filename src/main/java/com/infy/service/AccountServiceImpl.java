package com.infy.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.dto.AccountDTO;
import com.infy.dto.TransferfundDTO;
import com.infy.dto.TrasactionDto;
import com.infy.entity.Account;
import com.infy.entity.Transaction;
import com.infy.exception.AccountException;
import com.infy.mapper.AccountMapper;
import com.infy.repository.AccountRepository;
import com.infy.repository.TransactionRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public AccountDTO saveAccount(AccountDTO accountDTO) {

		Account mapToaccount = AccountMapper.mapToaccount(accountDTO);

		Account save = accountRepository.save(mapToaccount);

		return AccountMapper.mapToAccountDto(save);

	}

	@Override
	public AccountDTO getAccount(Long id) {

		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new AccountException("id is not present"));
		return AccountMapper.mapToAccountDto(account);

	}

	@Override
	public AccountDTO deposit(Long id, Double amount) {

		// checking the account id availbe ornot
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Id is not present"));

		Double total = account.getBalance() + amount;
		account.setBalance(total);
		Account save = accountRepository.save(account);
		
		// Transaction related logic 
		Transaction t=new Transaction();
		t.setAccountId(id);
		t.setAmount(amount);
		t.setTransactionType("DEPOSIT");
		t.setTimestamp(LocalDateTime.now());
		transactionRepository.save(t);
		
		
		return AccountMapper.mapToAccountDto(save);

	}
	@Override
	public AccountDTO withdraw(Long id, Double amount) {
		// checking the account id availbe ornot
				Account account = accountRepository.findById(id)
						.orElseThrow(() -> new RuntimeException("Id is not present"));

				Double total = account.getBalance() - amount;
				account.setBalance(total);
				Account save = accountRepository.save(account);
				
				Transaction t=new Transaction();
				t.setAccountId(id);
				t.setAmount(amount);
				t.setTransactionType("WITHDRAW");
				t.setTimestamp(LocalDateTime.now());
				transactionRepository.save(t);
				
				return AccountMapper.mapToAccountDto(save);

			}
	@Override
	public List<AccountDTO> findAll() {
		List<Account> accounts = accountRepository.findAll();
		 return accounts.stream()
				.map((account)->AccountMapper.mapToAccountDto(account))
				.collect(Collectors.toList());
	
	}
	@Override
	public void transferFounds(TransferfundDTO transferfundDTO) {
		
		
		
		 Account fromAccount = accountRepository.findById(transferfundDTO.fromAccountID())
				.orElseThrow(()->new RuntimeException("Account id not exist"));
		
		 //Retrive the amount which we are going to send
		 
		 Account toAccount = accountRepository.findById(transferfundDTO.toAccountID())
		 .orElseThrow(()->new RuntimeException("Account id is not exist"));
		 
		 
		 if(fromAccount.getBalance()<transferfundDTO.amount()) {
			 throw  new RuntimeException("Insufficent Amount");
		 }
		 
		 //Debit the amount from the amount
		 
		 fromAccount.setBalance(fromAccount.getBalance()-transferfundDTO.amount());
		 
		 toAccount.setBalance(toAccount.getBalance()+transferfundDTO.amount());
		 
		 accountRepository.save(fromAccount);
		 accountRepository.save(toAccount);
		 
		 Transaction t=new Transaction();
		 t.setAccountId(transferfundDTO.fromAccountID());
		 t.setAmount( transferfundDTO.amount());
		 t.setTransactionType("TRANSFER");
		 t.setTimestamp(LocalDateTime.now());
		 transactionRepository.save(t);
		 
		
	}
	@Override
	public List<TrasactionDto> getTransactionById(Long id) {
		 List<Transaction> transEntity = transactionRepository
				.findByAccountIdOrderByTimestampDesc(id);
		 
		 return transEntity.stream().map((trans)->convertEntityToDto(trans))
		 .collect(Collectors.toList());
		
		
	}
	
	private TrasactionDto convertEntityToDto(Transaction transaction) {
		TrasactionDto dto=new TrasactionDto(
				
				transaction.getId(), transaction.getAccountId(),
				transaction.getTransactionType(), 
				transaction.getAmount(), 
				transaction.getTimestamp());
		return dto;
		
		
	
	
}}
