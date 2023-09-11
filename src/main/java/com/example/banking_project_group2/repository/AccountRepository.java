package com.example.banking_project_group2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.banking_project_group2.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
	
	public Account findById(int account_no);
	
}
