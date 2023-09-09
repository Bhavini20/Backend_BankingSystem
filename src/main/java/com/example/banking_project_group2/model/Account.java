package com.example.banking_project_group2.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.transaction.Transaction;

@Entity
@Table(name="account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int account_no;
	
	@Column
	private String account_type;
	
	@Column
	private int balance;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="cust_id")
	private Customer cust_id;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="transact_id")
	private List<Transaction> transaction = new ArrayList<>();
	
	public Account() {
		
	}
	
	public Account(Integer account_no, String account_type, Integer balance, Customer cust_id) {
		this.account_no = account_no;
		this.account_type = account_type;
		this.balance = balance;
		this.cust_id = cust_id;
	}

	public int getAccount_no() {
		return account_no;
	}

	public void setAccount_no(int account_no) {
		this.account_no = account_no;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Customer getCust_id() {
		return cust_id;
	}

	public void setCust_id(Customer cust_id) {
		this.cust_id = cust_id;
	}
	
	
}
