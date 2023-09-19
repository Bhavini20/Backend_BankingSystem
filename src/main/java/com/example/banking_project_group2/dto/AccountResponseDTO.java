package com.example.banking_project_group2.dto;

import com.example.banking_project_group2.model.Account;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class AccountResponseDTO {

	private String account_type;


	private int account_no;
	private int balance;
	
//	  @NotBlank(message = "Customer first name is required")
	private String first_name;
	
//	  @NotBlank(message = "Customer last name is required")
	private String last_name;
	
//	  @NotBlank(message = "Address name is required")
	private String address;
	
//	  @NotBlank(message = "Aadhar number is required")
//	  @Size(min=12, max=12)
	  private String aadhar_no;

	  private boolean status;

	private String occupation;
	
//	@NotBlank(message = "Email id is required")
//	@Email
	private String emailId;
	
//	  @NotBlank(message = "mobile no is required")
//	  @Size(min=10, max=10)
	private String mobile;
	 
	
	  private Date dob;

	public AccountResponseDTO(Account A) {

		this.account_no = A.getAccount_no();
		this.status = A.getStatus();
		this.dob = A.getDob();
		this.account_type = A.getAccount_type();
		this.balance = A.getBalance();
		this.emailId = A.getEmailId();
		this.mobile = A.getMobile();
		this.address = A.getAddress();
		this.aadhar_no = A.getAadhar_no();
		this.first_name = A.getFirst_name();
		this.last_name = A.getLast_name();
		this.occupation = A.getOccupation();

	}

//	  private boolean intBanking;
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobile() {
		return mobile;
	}

	public boolean getStatus() { return status; }

	public void setStatus(boolean status) { this.status = status; }

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
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
