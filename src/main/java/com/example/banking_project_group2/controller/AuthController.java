package com.example.banking_project_group2.controller;

import com.example.banking_project_group2.dto.LoginDTO;
import com.example.banking_project_group2.dto.RegisterDTO;
import com.example.banking_project_group2.model.Customer;
import com.example.banking_project_group2.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public Customer register(@Valid @RequestBody RegisterDTO registerDTO){
        if(customerRepository.findByUsername(registerDTO.getUsername()) != null){
            return new Customer(0, "null", "null", 0);
        }

        Customer customer = new Customer();
        customer.setUsername(registerDTO.getUsername());
        customer.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        return customerRepository.save(customer);

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDTO loginDTO){
        System.out.println("[/login]");
        Customer customer = customerRepository.findByUsername(loginDTO.getUsername());
        if(customer == null) return new ResponseEntity<>("No such user found", HttpStatusCode.valueOf(403));

        if(passwordEncoder.matches(loginDTO.getPassword(), customer.getPassword())){
            return new ResponseEntity<>("User logged in", HttpStatusCode.valueOf(200));
        }

        return new ResponseEntity<>("Wrong password", HttpStatusCode.valueOf(403));
    }

}
