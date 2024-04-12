package com.infy.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infy.dto.AccountDTO;
import com.infy.dto.TransferfundDTO;
import com.infy.dto.TrasactionDto;
import com.infy.mapper.AccountMapper;
import com.infy.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	private AccountService service;

	@PostMapping("/account")
	public ResponseEntity<AccountDTO> save(@RequestBody AccountDTO accountDTO) {

		AccountDTO saveAccount = service.saveAccount(accountDTO);

		return new ResponseEntity<>(saveAccount, HttpStatus.CREATED);

	}

	@GetMapping("/account/{id}")
	public ResponseEntity<AccountDTO> getAccount(@PathVariable Long id) {

		AccountDTO account = service.getAccount(id);

		return new ResponseEntity<>(account, HttpStatus.OK);

	}
	
	
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDTO> depositAmount(@PathVariable Long id, @RequestBody Map<String, Double> request) {

		Double amount = request.get("amount");

		AccountDTO deposit = service.deposit(id, amount);

		return new ResponseEntity<>(deposit, HttpStatus.OK);
	
	}
	

	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDTO> withdrawAmount(@PathVariable Long id, @RequestBody Map<String, Double> request) {

		Double amount = request.get("amount");

		AccountDTO deposit = service.withdraw(id, amount);

		return new ResponseEntity<>(deposit, HttpStatus.OK);
	
	}
	@GetMapping("/account")
	public ResponseEntity<List<AccountDTO>> findAll(){
		List<AccountDTO> findAll = service.findAll();
		return new ResponseEntity<>(findAll, HttpStatus.OK);
	}

	@PostMapping("/transfer")
	public ResponseEntity<String> transferFounds(@RequestBody TransferfundDTO dto){
		service.transferFounds(dto);
		return ResponseEntity.ok("Transfer successfull with given :"+dto.amount());
	}
	
	
	@GetMapping("/{id}/transactions")
	public ResponseEntity<List<TrasactionDto>> getTranctions(@PathVariable Long id) {
       List<TrasactionDto> transactionById = service.getTransactionById(id);

		return new ResponseEntity<>(transactionById, HttpStatus.OK);

	}
	
	
}
