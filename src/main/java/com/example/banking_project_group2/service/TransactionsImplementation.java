package com.example.banking_project_group2.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banking_project_group2.dto.TransactionsDTO;
import com.example.banking_project_group2.exceptions.BalanceExceptions;
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
	
	

	public Transactions saveTransaction(TransactionsDTO transaction) throws BalanceExceptions {
		Transactions t = new Transactions();
		
		int facc = transaction.getFrom_acc();
		Account f_acc = acc.findById(facc);
		
		t.setAmount(transaction.getAmount());
		
		int tacc = transaction.getTo_acc();
		Account t_acc = acc.findById(tacc);
		
		
		
		if(!f_acc.getStatus())
			throw new BalanceExceptions("Your account is inactive!");
		
		if(!t_acc.getStatus())
			throw new BalanceExceptions("Receiver's account is Inactive!");
			
		t.setFrom_acc(f_acc);
		t.setTo_acc(t_acc);
		t.setTrans_time(new Date());
		
		if(f_acc.getBalance()-transaction.getAmount()<0) {
			t.setStatus(false);
			tc.save(t); 
			throw new BalanceExceptions("Insufficient Balance!");
		}
		else {
			f_acc.setBalance(f_acc.getBalance()-transaction.getAmount());
			t.setStatus(true);
		}
	
		t_acc.setBalance(t_acc.getBalance()+transaction.getAmount());
	

		return tc.save(t); 
	}

}
