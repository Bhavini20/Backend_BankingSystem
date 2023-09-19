package com.example.banking_project_group2.service;

import java.util.List;

import com.example.banking_project_group2.dto.AccountDTO;
import com.example.banking_project_group2.dto.StatusDTO;
import com.example.banking_project_group2.model.Account;
import com.example.banking_project_group2.model.Customer;

public interface AccountService {

	public Account addAccount(AccountDTO acc, Customer customer);
	public List<Account> viewAccounts(String token);

	public List<Account> getAllAccounts();

	public Account setAccountStatus(StatusDTO status);
}
