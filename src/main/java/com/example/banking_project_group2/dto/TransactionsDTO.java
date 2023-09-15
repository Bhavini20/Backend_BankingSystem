package com.example.banking_project_group2.dto;

import java.util.Date;


import com.example.banking_project_group2.model.Transactions;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TransactionsDTO {
	
	private int transaction_id;
	private int amount;
	private Date trans_time;
	private int from_acc;
	private int to_acc;
	private boolean status;
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getTrans_time() {
		return trans_time;
	}
	public void setTrans_time(Date trans_time) {
		this.trans_time = trans_time;
	}
	public int getFrom_acc() {
		return from_acc;
	}
	public void setFrom_acc(int from_acc) {
		this.from_acc = from_acc;
	}
	public int getTo_acc() {
		return to_acc;
	}
	public void setTo_acc(int to_acc) {
		this.to_acc = to_acc;
	}
	
//	public TransactionsDTO(Transactions T) {
//		
////		this.
//	}
	
	
	
	public TransactionsDTO(Transactions T) {

		this.amount = T.getAmount();
		this.from_acc = T.getFrom_acc().getAccount_no();
		this.to_acc =T.getTo_acc().getAccount_no();
		this.trans_time = T.getTrans_time();
		this.status=T.isStatus();
		// TODO Auto-generated constructor stub
	}
     public TransactionsDTO() {
    	 
     }
	
	
}
