package com.example.banking_project_group2.controller;

import com.example.banking_project_group2.dto.LoginDTO;
import com.example.banking_project_group2.dto.RegisterDTO;
import com.example.banking_project_group2.model.Customer;
import com.example.banking_project_group2.repository.CustomerRepository;
import com.example.banking_project_group2.security.JWTGen;
import com.example.banking_project_group2.dto.AuthResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private JWTGen jwtgen;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JWTGen jwtgen, CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtgen = jwtgen;
    }

    @PostMapping("/register")
    public Customer register(@RequestBody RegisterDTO registerDTO){
    	System.out.println("[/register]");
        if(customerRepository.findByUsername(registerDTO.getUsername()) != null){
            return new Customer(0, "null", "null", 0);
        }

        Customer customer = new Customer();
        customer.setUsername(registerDTO.getUsername());
        customer.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        return customerRepository.save(customer);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO){
        System.out.println("[/login]");
        Authentication auth = authenticationManager.authenticate(
        		new UsernamePasswordAuthenticationToken(
        				loginDTO.getUsername(),
        				loginDTO.getPassword()
        				)
        		);
//        System.out.println("";)
        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = jwtgen.generateToken(auth);
        
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

}
