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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	@Column(name = "name")
	private String name;
	@Column(name = "password")
	private String password;
	@Column(name = "age")
	private int age;
	
	public Customer() {
		
	}
	
	public Customer(int id, String name, String password, int age) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.age = age;
	}
	
	
}