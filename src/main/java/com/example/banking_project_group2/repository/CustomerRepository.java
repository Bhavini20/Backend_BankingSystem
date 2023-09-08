package com.example.banking_project_group2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.banking_project_group2.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	public Customer findById(int id);
}
