package com.example.banking_project_group2.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banking_project_group2.dto.TransactionsDTO;
import com.example.banking_project_group2.model.Account;
import com.example.banking_project_group2.model.Transactions;
import com.example.banking_project_group2.repository.AccountRepository;
import com.example.banking_project_group2.repository.TransactionsRepo;

@Service
public class TransactionsImplementation implements TransactionsService{
	
	@Autowired
	AccountRepository acc;
	
	@Autowired
	TransactionsRepo tc;
	
	public List<Transactions> viewToTransactions(int id) {
		Account ac = acc.findById(id);
		return ac.getToTransactions();
	}
	

	public List<Transactions> viewFromTransactions( int id) {
		Account ac = acc.findById(id);
		return ac.getFromTransactions();
	}
	
	public List<TransactionsDTO> viewAllTransactions(int id){
		Account ac = acc.findById(id);
		List<Transactions> to_trans = ac.getToTransactions();
		List<Transactions> from_trans = ac.getFromTransactions();
		List<Transactions> allTrans = Stream.concat(to_trans.stream(),from_trans.stream()).toList();
		
		List<TransactionsDTO> trans = new ArrayList<>();
		allTrans.forEach(t -> trans.add(new TransactionsDTO(t)));
		
		return trans;
	}

	public Transactions saveTransaction(TransactionsDTO transaction) {
		Transactions t = new Transactions();
		
		t.setAmount(transaction.getAmount());
		
		
		int facc = transaction.getFrom_acc();
		Account f_acc = acc.findById(facc);
		f_acc.setBalance(f_acc.getBalance()-transaction.getAmount());
		t.setStatus(true);
		
		int tacc = transaction.getTo_acc();
		Account t_acc = acc.findById(tacc);
		t_acc.setBalance(t_acc.getBalance()+transaction.getAmount());
		
		
		t.setFrom_acc(f_acc);
		t.setTo_acc(t_acc);
		t.setTrans_time(new Date());
		return tc.save(t); 
	}

}
