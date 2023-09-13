package com.example.banking_project_group2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking_project_group2.model.Customer;
import com.example.banking_project_group2.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService custSer;
	
	
	@GetMapping("/id/{id}")
	public Customer getCustomerById(@PathVariable int id) {
		return custSer.getCustomerById(id);
	}

	@GetMapping("/username/{username}")
	public Customer getCustomerByUsername(@PathVariable String username){
		return custSer.getCustomerByUsername(username);
	}

	@PostMapping("/register")
	public Customer addCustomer(@RequestBody Customer cust) {
		return custSer.addCustomer(cust);
	}
}
