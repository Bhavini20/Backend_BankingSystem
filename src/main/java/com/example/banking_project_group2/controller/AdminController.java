package com.example.banking_project_group2.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.banking_project_group2.dto.AccountResponseDTO;
import com.example.banking_project_group2.dto.StatusDTO;
import com.example.banking_project_group2.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.banking_project_group2.repository.AccountRepository;
import com.example.banking_project_group2.security.JWTGen;
import com.example.banking_project_group2.dto.AccountDTO;
import com.example.banking_project_group2.model.Account;


@Controller
@CrossOrigin
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AccountService accountService;
//	@Autowired
//	private AccountRepository accountrepo;
	
	@Autowired
	private JWTGen jwtgen;
	
	@GetMapping("/allAccounts")
	private ResponseEntity<List<AccountResponseDTO>> allAccounts(@RequestHeader(name="Authorization") String token){
		String username = jwtgen.getUsernameFromToken(token.substring(7));
		if(!username.equals("admin")) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		List<Account> accounts = accountService.getAllAccounts();
		List<AccountResponseDTO> acc = new ArrayList<>();

		accounts.forEach(account -> {
			acc.add(new AccountResponseDTO(account));
		});

		return new ResponseEntity<>(acc, HttpStatus.OK);
	}

	@PostMapping("/setStatus")
	@CrossOrigin
	private ResponseEntity<String> setStatus(@RequestBody StatusDTO status, @RequestHeader(name="Authorization") String token){
		String username = jwtgen.getUsernameFromToken(token.substring(7));
		if(!username.equals("admin")) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		Account account = accountService.setAccountStatus(status);

		return new ResponseEntity<>("Status updated to " + account.getStatus(), HttpStatus.OK);
	}
	
}
