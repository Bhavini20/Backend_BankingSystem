package com.example.banking_project_group2.controller;

import java.util.List;

import com.example.banking_project_group2.dto.TransactionsResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import com.example.banking_project_group2.dto.TransactionsDTO;
import com.example.banking_project_group2.exceptions.BalanceExceptions;
import com.example.banking_project_group2.exceptions.ResourceNotFoundException;
import com.example.banking_project_group2.model.Transactions;
import com.example.banking_project_group2.repository.AccountRepository;
import com.example.banking_project_group2.repository.TransactionsRepo;
import com.example.banking_project_group2.service.TransactionsService;

@RestController
@CrossOrigin
@RequestMapping("/api/transactions")
public class TransactionsController {
	
	@Autowired
	AccountRepository acc;
	
	@Autowired
	TransactionsRepo tc;
	
	@Autowired
	TransactionsService ts;
	
	
	
	@GetMapping("/viewToTransactions/id/{id}")
	public List<Transactions> viewToTransactions(@PathVariable int id) throws ResourceNotFoundException {
		return ts.viewToTransactions(id);
	}
	
	@GetMapping("/viewFromTransactions/id/{id}")
	public List<Transactions> viewFromTransactions(@PathVariable int id) throws ResourceNotFoundException{
		return ts.viewFromTransactions(id);
	}
	
	
	@GetMapping("/viewAllTransactions/id/{id}") 
	public List<TransactionsResponseDTO> viewAllTransactions(@PathVariable int id) throws BalanceExceptions{
		return ts.viewAllTransactions(id);
	}
	
	@PostMapping("/saveTransactions")
	public Transactions saveTransaction(@RequestBody TransactionsDTO transaction) throws BalanceExceptions {
//		if(!(transaction.getStatus() && transaction.get))
		return ts.saveTransaction(transaction); 
	}
	
//	@PostMapping("/withdrawal/id/{id}")
//	public Transactions saveTransaction(@RequestBody TransactionsDTO transaction) throws BalanceExceptions {
////		if(!(transaction.getStatus() && transaction.get))
//		return ts.saveTransaction(transaction); 
//	}
	


}
