package com.example.banking_project_group2.exceptions;

import java.util.Date;

public class ErrorDetails {
	private Date timestamp;
	 private String message;
	

	 public ErrorDetails(Date timestamp, String message) {
	  super();
	  this.timestamp = timestamp;
	  this.message = message;
	  
	 }

	 public Date getTimestamp() {
	  return timestamp;
	 }

	 public String getMessage() {
	  return message;
	 }

	
	}