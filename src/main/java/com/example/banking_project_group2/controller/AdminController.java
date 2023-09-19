package com.example.banking_project_group2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.banking_project_group2.repository.AccountRepository;
import com.example.banking_project_group2.security.JWTGen;
import com.example.banking_project_group2.dto.AccountDTO;
import com.example.banking_project_group2.model.Account;


@Controller
@CrossOrigin
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private AccountRepository accountrepo;
	
	@Autowired
	private JWTGen jwtgen;
	
	@GetMapping("/allAccounts")
	private ResponseEntity<List<AccountDTO>> allAccounts(@RequestHeader(name="Authorization") String token){
		String username = jwtgen.getUsernameFromToken(token.substring(7));
		if(username.equals("admin") == false) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		List<Account> accounts = accountrepo.findAll();
		List<AccountDTO> acc;
		
		
		return new ResponseEntity<>(acc, HttpStatus.OK);
	}
	
}
