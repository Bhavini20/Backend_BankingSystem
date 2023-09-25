package com.example.banking_project_group2.service;

import java.util.List;

import com.example.banking_project_group2.dto.StatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.banking_project_group2.dto.AccountDTO;
import com.example.banking_project_group2.model.Account;
import com.example.banking_project_group2.model.Customer;
import com.example.banking_project_group2.repository.AccountRepository;
import com.example.banking_project_group2.repository.CustomerRepository;
import com.example.banking_project_group2.security.JWTGen;

@Service
public class AccountImplementation implements AccountService {
	
	@Autowired
	private AccountRepository accRepo;
	
	@Autowired
	private JWTGen jwtgen;
	
	@Autowired
	private CustomerRepository cust;
	
	public Account addAccount(AccountDTO account, Customer customer) {
//		int custId = account.getCust_id();
//		Customer customer = cust.findById(custId);
		System.out.println("In addAccountService");
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
		ac.setStatus(true);
		ac.setMobile(account.getMobile());
		return accRepo.save(ac);
	}
	
	public List<Account> viewAccounts(String username) {

		System.out.println("[/viewAccounts]");
		Customer customer = cust.findByUsername(username);

		return customer.getAccounts();

	}

	public List<Account> getAllAccounts(){
		return accRepo.findAll();
	}

	public Account setAccountStatus(StatusDTO status){
		Account account = accRepo.findById(status.getAccount_no());
		account.setStatus(status.getStatus());
		return accRepo.save(account);
	}

}
