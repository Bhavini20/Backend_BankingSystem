package com.example.banking_project_group2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.banking_project_group2.model.Customer;
import com.example.banking_project_group2.repository.CustomerRepository;

@Service
public class CustomerImplementation implements CustomerService {
	
	@Autowired
	private CustomerRepository custrepo;
	
	
	public Customer getCustomerById(int id) {
		System.out.println("[/api/customer/id]");
		return custrepo.findById(id);
	}


	public Customer getCustomerByUsername(String username){
		return custrepo.findByUsername(username);
		}
	
	public Customer addCustomer(Customer cust) {
		return custrepo.save(cust);
	}
}
