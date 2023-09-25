package com.example.banking_project_group2.service;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.banking_project_group2.repository.AccountRepository;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AccountTest {

	@InjectMocks
	private AccountImplementation accSer;
	
	@Mock
	private AccountRepository accRepo;
	
	
}
