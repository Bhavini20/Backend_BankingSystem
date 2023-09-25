package com.example.banking_project_group2.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	private String first_name;
	
	@Column
	private String last_name;
	
	@Column
	private String address;
	
	@Column
	private String aadhar_no;
	
	@Column
	private String occupation;
	
	@Column
	private String emailId;
	
	@Column
	private String mobile;
	
	@Column
	private boolean status;
	
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Column
	private Date dob;
	
//	@Column
//	private boolean accStatus;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="cust_id")
	@JsonBackReference
	private Customer cust_id;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="to_acc")
	@JsonManagedReference
	private List<Transactions> to_transaction = new ArrayList<>();
//	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="from_acc")
	@JsonManagedReference
	private List<Transactions> from_transaction = new ArrayList<>();
	
	public List<Transactions> getToTransactions(){
		return to_transaction;
	}

	public void setTo_transaction(List<Transactions> to_transaction) { this.to_transaction = to_transaction; }

	public void setFrom_transaction(List<Transactions> from_transaction) { this.from_transaction = from_transaction; }

	public List<Transactions> getFromTransactions(){
		return from_transaction;
	}
	
	public Account() {
		
	}
	

	public Account(int account_no, String account_type, int balance, String first_name, String last_name,
			String address, String aadhar_no, String occupation, String emailId, String mobile, Date dob,
			Customer cust_id) {
		super();
		this.account_no = account_no;
		this.account_type = account_type;
		this.balance = balance;
		this.first_name = first_name;
		this.last_name = last_name;
		this.address = address;
		this.aadhar_no = aadhar_no;
		this.occupation = occupation;
		this.emailId = emailId;
		this.mobile = mobile;
		this.dob = dob;
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
	


	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAadhar_no() {
		return aadhar_no;
	}

	public void setAadhar_no(String aadhar_no) {
		this.aadhar_no = aadhar_no;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	
}
