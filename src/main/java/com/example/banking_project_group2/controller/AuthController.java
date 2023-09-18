package com.example.banking_project_group2.controller;

import com.example.banking_project_group2.dto.AuthResponseDTO;
import com.example.banking_project_group2.dto.LoginDTO;
import com.example.banking_project_group2.dto.RegisterDTO;
import com.example.banking_project_group2.model.Customer;
import com.example.banking_project_group2.repository.CustomerRepository;
import com.example.banking_project_group2.security.JWTGen;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
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
    public ResponseEntity<Customer> register(@Valid @RequestBody RegisterDTO registerDTO){
        if(customerRepository.findByUsername(registerDTO.getUsername()) != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Customer customer = new Customer();
        customer.setUsername(registerDTO.getUsername());
        customer.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        Customer savedCustomer = customerRepository.save(customer);

        return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO){
        System.out.println("[/login]");
        Authentication auth = authenticationManager.authenticate(
        		new UsernamePasswordAuthenticationToken(
        				loginDTO.getUsername(),
        				loginDTO.getPassword()
        				)
        		);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = jwtgen.generateToken(auth);
        
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

}
