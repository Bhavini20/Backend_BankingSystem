package com.example.banking_project_group2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking_project_group2.dto.AccountDTO;
import com.example.banking_project_group2.model.Account;
import com.example.banking_project_group2.model.Customer;
import com.example.banking_project_group2.repository.AccountRepository;
import com.example.banking_project_group2.repository.CustomerRepository;
import com.example.banking_project_group2.security.JWTGen;
import com.example.banking_project_group2.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/account")
public class AccountController {

	@Autowired
	private AccountRepository acc;

	@Autowired
	private JWTGen jwtgen;

	@Autowired
	private CustomerRepository cust;

	@Autowired
	AccountService accSer;

	@PostMapping("/saveAccount")
	public Account addAccount(@Valid @RequestBody AccountDTO account, @RequestHeader(name = "Authorization") String token) {
		String username = jwtgen.getUsernameFromToken(token.substring(7));
		Customer customer = cust.findByUsername(username);
		return accSer.addAccount(account, customer);
	}

	@GetMapping("/viewAccounts")
	public List<Account> viewAccounts(@Valid @RequestHeader(name = "Authorization") String token) {
		return accSer.viewAccounts(token);
	}

}
