package com.example.banking_project_group2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.banking_project_group2.model.Account;
import com.example.banking_project_group2.model.Transactions;

public interface TransactionsRepo extends JpaRepository<Transactions, Integer> {

	public Transactions findById(int trans_id);
}
