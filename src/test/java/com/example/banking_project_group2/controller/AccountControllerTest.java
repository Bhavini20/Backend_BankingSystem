package com.example.banking_project_group2.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.banking_project_group2.dto.AccountDTO;
import com.example.banking_project_group2.model.Account;
import com.example.banking_project_group2.model.Customer;
import com.example.banking_project_group2.repository.AccountRepository;
import com.example.banking_project_group2.repository.CustomerRepository;
import com.example.banking_project_group2.security.JWTGen;
import com.example.banking_project_group2.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Component("test_jwt")
//class JWTGenTest extends JWTGen{
//    @Override
//    public String getUsernameFromToken(String token){
//        if(token.equals("token.t0.t3st1")) return "testuser1";
//        else if(token.equals("token.t0.t3st2")) return "testuser2";
//        return "";
//    }
//
//    @Override
//    public boolean validateToken(String token){
//        return token.equals("token.t0.t3st1") || token.equals("token.to.t3st2");
//    }
//
//}

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {


    @MockBean
    private JWTGen jwtgen;

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private AccountService accountService;

    @Autowired
    private MockMvc mvc;

    Customer test_from_cust;
    Customer test_to_cust;
    Account test_from_acc;
    Account test_to_acc;

    @BeforeEach
    public void init() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        test_from_cust = new Customer(1, "testuser1", "password", 20);
        test_from_acc = new Account(1, "Savings", 2000, "Test",
                "One", "Test add", "123456789012", "Test Occ",
                "test1@mail.com", "1234567890", dateFormat.parse("29/04/2001"), test_from_cust);


        test_to_cust = new Customer(2, "testuser2", "password", 20);
        test_to_acc = new Account(2, "Salary", 2000, "Test",
                "Two", "Test add", "123456789012", "Test Occ",
                "test2@mail.com", "1234567890", dateFormat.parse("29/04/2001"), test_to_cust);
    }

    public static String asJson(final Object obj){
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void AccountController_SaveAccount() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        AccountDTO accountDTO = new AccountDTO("Savings", 2000, "Test",
                "One", "Test add", "123456789012", "Test Occ",
                "test1@mail.com", "1234567890", dateFormat.parse("29/04/2001"));


        when(jwtgen.getUsernameFromToken(Mockito.anyString())).thenReturn("testuser1");
        when(customerRepository.findByUsername(Mockito.anyString())).thenReturn(test_from_cust);
        when(accountService.addAccount(accountDTO, test_from_cust)).thenReturn(test_from_acc);

        mvc.perform(post("/api/account/saveAccount")
                .header("Authorization", "test.jwt.t0k3n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(accountDTO)))
                .andExpect(status().isOk())
        ;


    }
    
    @Test
    public void AccountController_ViewAccount() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        List<Account> accs = List.of(test_from_acc);


        when(jwtgen.getUsernameFromToken(Mockito.anyString())).thenReturn("testuser1");
        when(customerRepository.findByUsername(Mockito.anyString())).thenReturn(test_from_cust);
        when(accountService.viewAccounts(Mockito.anyString())).thenReturn(accs);

        mvc.perform(get("/api/account/viewAccounts")
                .header("Authorization", "test.jwt.t0k3n")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;


    }
    
    @Test
    public void AccountController_ViewAccountNumbers() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        List<Account> accs = List.of(test_from_acc);


        when(jwtgen.getUsernameFromToken(Mockito.anyString())).thenReturn("testuser1");
        when(customerRepository.findByUsername(Mockito.anyString())).thenReturn(test_from_cust);
        when(accountService.viewAccounts(Mockito.anyString())).thenReturn(accs);

        mvc.perform(get("/api/account/viewAccountNumbers")
                .header("Authorization", "test.jwt.t0k3n")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]", is(1)))
        ;


    }

}
