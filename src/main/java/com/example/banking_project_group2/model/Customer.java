package com.example.banking_project_group2.model;
//package com.example.model;

//import jakarta.persistence.Entity;
import jakarta.persistence.*;

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