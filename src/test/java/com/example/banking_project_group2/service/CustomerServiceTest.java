package com.example.banking_project_group2.service;

import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.banking_project_group2.model.Customer;
import com.example.banking_project_group2.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
	
	@Mock
	private CustomerRepository custRepo;
	
	@InjectMocks
	private CustomerImplementation custSer;	
	
	
	private Customer cust;
	
	@BeforeEach
	public void init() {
		cust = new Customer(1, "test", "password", 2);
	}
	
	@Test
	public void CustomerService_FindById() {
		Customer customer = new Customer(1, "testuser", "password", 2);
		when(custRepo.findById(1)).thenReturn(customer);
		
		Customer savedCust = custSer.getCustomerById(1);
		Assertions.assertThat(savedCust).isNotNull();
	}
	
	@Test
	public void CustomerService_addCust() {
		Customer customer = new Customer(1, "test", "password", 2);
		when(custRepo.save(Mockito.any(Customer.class))).thenReturn(customer);
		
		Customer savedCust = custSer.addCustomer(cust);
		Assertions.assertThat(savedCust).isNotNull();	
	}
	
	@Test
	public void CustomerService_findByUsername() {
		String testusername = "test";
		when(custRepo.findByUsername(testusername)).thenReturn(cust);
		
		Customer getCust = custSer.getCustomerByUsername(testusername);
		Assertions.assertThat(getCust).isNotNull();
	}
		

}
