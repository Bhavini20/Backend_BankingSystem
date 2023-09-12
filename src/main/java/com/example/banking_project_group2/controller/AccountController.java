package com.example.banking_project_group2.controller;

import java.util.List;

import com.example.banking_project_group2.security.JWTGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.banking_project_group2.dto.AccountDTO;
import com.example.banking_project_group2.model.Account;
import com.example.banking_project_group2.model.Customer;
import com.example.banking_project_group2.repository.AccountRepository;
import com.example.banking_project_group2.repository.CustomerRepository;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/account")
public class AccountController {
	
	@Autowired
	private AccountRepository acc;

	@Autowired
	private JWTGen jwtgen;
	
	@Autowired
	private CustomerRepository cust;
	
	@PostMapping("/saveAccount")
	public Account addAccount(@RequestBody AccountDTO account, @RequestHeader(name = "Authorization") String token) {
		String username = jwtgen.getUsernameFromToken(token.substring(7));
		Customer customer = cust.findByUsername(username);
//		int custId = account.getCust_id();
//		Customer customer = cust.findById(custId);
		Account ac = new Account();
		ac.setAadhar_no(account.getAadhar_no());
		ac.setAccount_type(account.getAccount_type());
		ac.setAddress(account.getAddress());
		ac.setBalance(account.getBalance());
		ac.setCust_id(customer);
		ac.setFirst_name(account.getFirst_name());
		ac.setLast_name(account.getLast_name());
		ac.setOccupation(account.getOccupation());
		ac.setDob(account.getDob());
		ac.setEmailId(account.getEmailId());
		ac.setMobile(account.getMobile());
		return acc.save(ac);
	}

	@GetMapping("/viewAccounts")
	public List<Account> viewAccounts(@RequestHeader(name = "Authorization") String token) {

		System.out.println("[/viewAccounts]");
		String username = jwtgen.getUsernameFromToken(token.substring(7));
		Customer customer = cust.findByUsername(username);

		return customer.getAccounts();

	}

}
