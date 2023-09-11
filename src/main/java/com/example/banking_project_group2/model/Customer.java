package com.example.banking_project_group2.model;
//package com.example.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
//import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "username",nullable=false,unique=true)
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "age")
	private int age;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="cust_id")
	private List<Account> account = new ArrayList<>();
	
	public Customer() {
		
	}
	
	public Customer(int id, String username, String password, int age) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.age = age;
	}
	
	
}