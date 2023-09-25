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
	
	private int amount;
	private int from_acc;
	private int to_acc;

	public TransactionsDTO(int amount, int from_acc, int to_acc) {
		this.amount = amount;
		this.from_acc = from_acc;
		this.to_acc = to_acc;
	}


	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
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

	public TransactionsDTO(Transactions T) {

		this.amount = T.getAmount();
		this.from_acc = T.getFrom_acc().getAccount_no();
		this.to_acc =T.getTo_acc().getAccount_no();

	}

	public TransactionsDTO(){

	}

}
