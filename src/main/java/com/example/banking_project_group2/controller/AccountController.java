package com.example.banking_project_group2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking_project_group2.dto.AccountDTO;
import com.example.banking_project_group2.model.Account;
import com.example.banking_project_group2.model.Customer;
import com.example.banking_project_group2.repository.AccountRepository;
import com.example.banking_project_group2.repository.CustomerRepository;

@RestController
@RequestMapping("/api/account")
public class AccountController {
	
	@Autowired
	private AccountRepository acc;
	
	@Autowired
	private CustomerRepository cust;
	
	@PostMapping("/saveAccount")
	public Account addAccount(@RequestBody AccountDTO account) {
		int custId = account.getCust_id();
		Customer customer = cust.findById(custId);
		Account ac = new Account();
		ac.setAadhar_no(account.getAadhar_no());
		ac.setAccount_type(account.getAccount_type());
		ac.setAddress(account.getAddress());
		ac.setBalance(account.getBalance());
		ac.setCust_id(customer);
		ac.setFirst_name(account.getFirst_name());
		ac.setLast_name(account.getLast_name());
		ac.setOccupation(account.getOccupation());
		return acc.save(ac);
	}

}
