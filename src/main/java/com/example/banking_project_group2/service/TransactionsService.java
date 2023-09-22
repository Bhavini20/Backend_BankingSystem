package com.example.banking_project_group2.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.example.banking_project_group2.dto.TransactionsDTO;
import com.example.banking_project_group2.dto.TransactionsResponseDTO;
import com.example.banking_project_group2.dto.WithdrawalDTO;
import com.example.banking_project_group2.exceptions.BalanceExceptions;
import com.example.banking_project_group2.exceptions.ResourceNotFoundException;
import com.example.banking_project_group2.model.Transactions;

public interface TransactionsService {

	public List<Transactions> viewToTransactions(int id) throws ResourceNotFoundException;
	public List<Transactions> viewFromTransactions(int id) throws ResourceNotFoundException;
	public List<TransactionsResponseDTO> viewAllTransactions(int id) throws BalanceExceptions;
	public Transactions saveTransaction(TransactionsDTO transaction) throws BalanceExceptions;
	public Transactions withdraw( WithdrawalDTO transaction) throws BalanceExceptions;
}
