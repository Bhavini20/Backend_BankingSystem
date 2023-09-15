package com.example.banking_project_group2.service;

import java.util.List;
import com.example.banking_project_group2.dto.TransactionsDTO;
import com.example.banking_project_group2.exceptions.BalanceExceptions;
import com.example.banking_project_group2.model.Transactions;

public interface TransactionsService {

	public List<Transactions> viewToTransactions(int id);
	public List<Transactions> viewFromTransactions(int id);
	public List<TransactionsDTO> viewAllTransactions(int id);
	public Transactions saveTransaction(TransactionsDTO transaction) throws BalanceExceptions;
}
