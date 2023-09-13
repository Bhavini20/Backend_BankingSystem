package com.example.banking_project_group2.service;

import com.example.banking_project_group2.model.Customer;

public interface CustomerService {

	public Customer getCustomerById(int id);
	public Customer getCustomerByUsername(String username);
	public Customer addCustomer(Customer cust); 
}
