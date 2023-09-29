package com.example.banking_project_group2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.banking_project_group2.dto.TransactionsDTO;
import com.example.banking_project_group2.dto.TransactionsResponseDTO;
import com.example.banking_project_group2.dto.WithdrawalDTO;
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

			return ts.saveTransaction(transaction);
	}
	
	@PostMapping("/withdrawal")
	public Transactions withdraw(@RequestBody WithdrawalDTO transaction) throws BalanceExceptions {
		return ts.withdraw(transaction); 
	}
	


}
