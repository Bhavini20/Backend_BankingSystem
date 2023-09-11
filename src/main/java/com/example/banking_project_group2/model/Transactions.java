package com.example.banking_project_group2.model;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="transaction")
public class Transactions{
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transaction_id;
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date trans_time;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="from_acc")
	private Account from_acc;	

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="to_acc")
	private Account to_acc;
	
	@Column
	private int amount;
	
	
	
	public Transactions() {
		
	}
	
	public Transactions(int transaction_id, Date trans_time, int amount, Account from_acc, Account to_acc) {
		super();
		this.transaction_id = transaction_id;
		this.trans_time = trans_time;
		this.from_acc = from_acc;
		this.to_acc = to_acc;
		this.amount = amount;
	}

	public int getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	public Date getTrans_time() {
		return trans_time;
	}

	public void setTrans_time(Date trans_time) {
		this.trans_time = trans_time;
	}

	public Account getFrom_acc() {
		return from_acc;
	}

	public void setFrom_acc(Account from_acc) {
		this.from_acc = from_acc;
	}

	public Account getTo_acc() {
		return to_acc;
	}

	public void setTo_acc(Account to_acc) {
		this.to_acc = to_acc;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	
	

}
