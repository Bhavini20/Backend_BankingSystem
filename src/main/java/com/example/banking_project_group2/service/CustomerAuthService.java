package com.example.banking_project_group2.service;

import com.example.banking_project_group2.model.Customer;
import com.example.banking_project_group2.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;

@Service
public class CustomerAuthService implements UserDetailsService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer cust = customerRepository.findByUsername(username);
        return new User(cust.getUsername(), cust.getPassword(), new HashSet<>());
    }
}