package com.infy.mapper;

import com.infy.dto.AccountDTO;
import com.infy.entity.Account;

public class AccountMapper {

	
	
	// converting AccountDto to Account.
	
	public static Account mapToaccount(AccountDTO dto) {
		Account account = new Account(
				dto.id() ,
				dto.accountHolderName(),
				dto.balance());
		return account;
	}
	
	
	// converting acccunt to accountDto
	public static AccountDTO mapToAccountDto(Account account) {
		
		AccountDTO dto=new AccountDTO(
				account.getId(),
				account.getAccountHolderName(),
				account.getBalance()
				);
		return dto;
	}

	
}
