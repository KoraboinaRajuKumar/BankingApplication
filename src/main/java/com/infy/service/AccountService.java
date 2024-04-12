package com.infy.service;

import java.util.List;

import com.infy.dto.AccountDTO;
import com.infy.dto.TransferfundDTO;
import com.infy.dto.TrasactionDto;

public interface AccountService {
	
	public AccountDTO saveAccount(AccountDTO accountDTO);
	
	public AccountDTO getAccount(Long id);
	
	public AccountDTO deposit(Long id,Double amount);
	
	public AccountDTO withdraw(Long id,Double amount);
	
	public List<AccountDTO> findAll();
	
	public void transferFounds(TransferfundDTO dto);
	
	public List<TrasactionDto> getTransactionById(Long id);
	

}
