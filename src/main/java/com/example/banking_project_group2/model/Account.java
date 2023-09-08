package com.example.banking_project_group2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
	
	@Column
	private int cust_id;
	
	public Account() {
		
	}
	
	public Account(Integer account_no, String account_type, Integer balance, Integer cust_id) {
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

	public int getCust_id() {
		return cust_id;
	}

	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	
	
}
