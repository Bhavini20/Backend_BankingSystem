package com.example.banking_project_group2.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.example.banking_project_group2.dto.StatusDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.banking_project_group2.dto.AccountDTO;
import com.example.banking_project_group2.model.Account;
import com.example.banking_project_group2.model.Customer;
import com.example.banking_project_group2.repository.AccountRepository;
import com.example.banking_project_group2.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class AccountTest {

	@InjectMocks
	private AccountImplementation accSer;
	
	@Mock
	private AccountRepository accRepo;
	
	@Mock
	private CustomerRepository cust;
	
	@Test 
	public void saveAccount() {
		Date d = new Date();
		AccountDTO account = new AccountDTO("salary",200, "bhavini","pratap","mumbai","234567890123","govt servant","singhbhavini@gmail.com", "8800533267" , d);
		Customer c = new Customer (1, "bhavini", "password", 10);
		Account ac = new Account();
		
		ac.setAadhar_no(account.getAadhar_no());
		ac.setAccount_type(account.getAccount_type());
		ac.setAddress(account.getAddress());
		ac.setBalance(account.getBalance());
		ac.setCust_id(c);
		ac.setFirst_name(account.getFirst_name());
		ac.setLast_name(account.getLast_name());
		ac.setOccupation(account.getOccupation());
		ac.setDob(account.getDob());
		ac.setEmailId(account.getEmailId());
		ac.setStatus(true);
		ac.setMobile(account.getMobile());
		
		when(accRepo.save(any(Account.class))).thenReturn(ac);
		
		Account savedAccount = accSer.addAccount(account, c);
		
		assertNotNull(savedAccount);	
	}
	
	@Test
	public void findAccounts() {
		accSer.getAllAccounts();
		verify(accRepo).findAll();
	}
	
	@Test
	public void viewAccounts() {
		Date d = new Date();
		AccountDTO account = new AccountDTO("salary",200, "bhavini","pratap","mumbai","234567890123","govt servant","singhbhavini@gmail.com", "8800533267" , d);
		Customer c = new Customer (1, "bhavini", "password", 10);
	
		Account ac = new Account();
		ac.setAadhar_no(account.getAadhar_no());
		ac.setAccount_type(account.getAccount_type());
		ac.setAddress(account.getAddress());
		ac.setBalance(account.getBalance());
		ac.setCust_id(c);
		ac.setFirst_name(account.getFirst_name());
		ac.setLast_name(account.getLast_name());
		ac.setOccupation(account.getOccupation());
		ac.setDob(account.getDob());
		ac.setEmailId(account.getEmailId());
		ac.setStatus(true);
		ac.setMobile(account.getMobile());
		
		AccountDTO account1 = new AccountDTO("saving",200, "bhavini","pratap","mumbai","234567890123","govt servant","singhbhavini@gmail.com", "8800533267" , d);
		Account ac1 = new Account();
		ac1.setAadhar_no(account1.getAadhar_no());
		ac1.setAccount_type(account1.getAccount_type());
		ac1.setAddress(account1.getAddress());
		ac1.setBalance(account1.getBalance());
		ac1.setCust_id(c);
		ac1.setFirst_name(account1.getFirst_name());
		ac1.setLast_name(account1.getLast_name());
		ac1.setOccupation(account1.getOccupation());
		ac1.setDob(account1.getDob());
		ac1.setEmailId(account1.getEmailId());
		ac1.setStatus(true);
		ac1.setMobile(account1.getMobile());
		
		List<Account> accounts = Arrays.asList(ac, ac1);
		c.setAccounts(accounts);		
		when(cust.findByUsername(c.getUsername())).thenReturn(c);
			
		List<Account> findAccount = accSer.viewAccounts(c.getUsername());
		
		assertEquals(accounts.size(), findAccount.size());
		
	}
	
	@Test
	public void setStatus() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Customer test_from_cust = new Customer(1, "test_from_cust", "password", 20);;
		Account test_acc = new Account(1, "Savings", 2000, "Test",
				"One", "Test add", "123456789012", "Test Occ",
				"test1@mail.com", "1234567890", dateFormat.parse("29/04/2001"), test_from_cust);
		test_acc.setStatus(false);
		Account ans_acc = new Account(1, "Savings", 2000, "Test",
				"One", "Test add", "123456789012", "Test Occ",
				"test1@mail.com", "1234567890", dateFormat.parse("29/04/2001"), test_from_cust);
		ans_acc.setStatus(true);

		when(accRepo.findById(1)).thenReturn(test_acc);
		when(accRepo.save(any(Account.class))).thenReturn(ans_acc);

		StatusDTO statusDTO = new StatusDTO(true, 1);

		Account reply_acc = accSer.setAccountStatus(statusDTO);

		assertEquals(reply_acc.getAccount_no(), 1);
        assertTrue(reply_acc.getStatus());
	}
	
	@Test
	public void getBalance() throws ParseException {
		int id = 1;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Customer test_from_cust = new Customer(1, "test_from_cust", "password", 20);;
		Account test_acc = new Account(1, "Savings", 2000, "Test",
				"One", "Test add", "123456789012", "Test Occ",
				"test1@mail.com", "1234567890", dateFormat.parse("29/04/2001"), test_from_cust);
		test_acc.setStatus(false);
		when(accRepo.findById(id)).thenReturn(test_acc);

		int balance = accSer.getBalance(id);

		assertEquals(balance, test_acc.getBalance());

	}
	
	
	
}
