package com.example.banking_project_group2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking_project_group2.dto.TransactionsDTO;
import com.example.banking_project_group2.model.Account;
import com.example.banking_project_group2.model.Transactions;
import com.example.banking_project_group2.repository.AccountRepository;
import com.example.banking_project_group2.repository.TransactionsRepo;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {
	
	@Autowired
	AccountRepository acc;
	
	@Autowired
	TransactionsRepo tc;
	
	@SuppressWarnings("deprecation")
	@GetMapping("/viewToTransactions/id/{id}")
	public List<Transactions> viewToTransactions(@PathVariable int id) {
		Account ac = acc.findById(id);
		return ac.getToTransactions();
	}
	
	@GetMapping("/viewFromTransactions/id/{id}")
	public List<Transactions> viewFromTransactions(@PathVariable int id) {
		Account ac = acc.findById(id);
		return ac.getFromTransactions();
	}
	
//	@GetMapping("/viewAllTransactions/id/{id}")
//	public List<>viewAllTransactions(@PathVariable int id){
//		Account ac = acc.findById(id);
//		List<Transactions> to_trans = ac.getToTransactions();
//		List<Transactions> from_trans = ac.getFromTransactions();
//		List<Transactions> allTrans = Stream.concat(to_trans.stream(),from_trans.stream()).toList();
//		
//		return allTrans;
//	}
	
	@GetMapping("/viewAllTransactions/id/{id}")
	public List<TransactionsDTO> viewAllTransactions(@PathVariable int id){
		Account ac = acc.findById(id);
		List<Transactions> to_trans = ac.getToTransactions();
		List<Transactions> from_trans = ac.getFromTransactions();
		List<Transactions> allTrans = Stream.concat(to_trans.stream(),from_trans.stream()).toList();
		
		List<TransactionsDTO> trans = new ArrayList<>();
		allTrans.forEach(t -> trans.add(new TransactionsDTO(t)));
		
		return trans;
	}
	
	@PostMapping("/saveTransactions")
	public Transactions saveTransaction(@RequestBody TransactionsDTO transaction) {
		Transactions t = new Transactions();
		
		t.setAmount(transaction.getAmount());
		
		
		int facc = transaction.getFrom_acc();
		Account f_acc = acc.getById(facc);
		f_acc.setBalance(f_acc.getBalance()-transaction.getAmount());
		
		int tacc = transaction.getTo_acc();
		Account t_acc = acc.getById(tacc);
		t_acc.setBalance(t_acc.getBalance()+transaction.getAmount());
		
		
		t.setFrom_acc(f_acc);
		t.setTo_acc(t_acc);
		
		
		return tc.save(t); 
	}


}
