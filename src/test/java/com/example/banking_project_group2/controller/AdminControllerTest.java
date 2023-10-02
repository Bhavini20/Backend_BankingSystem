package com.example.banking_project_group2.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

import com.example.banking_project_group2.dto.AccountResponseDTO;
import com.example.banking_project_group2.dto.StatusDTO;
import com.example.banking_project_group2.model.Account;
import com.example.banking_project_group2.model.Customer;
import com.example.banking_project_group2.security.JWTGen;
import com.example.banking_project_group2.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
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
import org.springframework.test.web.servlet.MvcResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

//import static reactor.core.publisher.Mono.when;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(AdminController.class)
public class AdminControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    AccountService accountService;

    @MockBean
    JWTGen jwtGen;


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
    public void AdminController_GetAccounts() throws Exception {

        AccountResponseDTO ac1 = new AccountResponseDTO(test_from_acc);
        AccountResponseDTO ac2 = new AccountResponseDTO(test_to_acc);

        when(jwtGen.getUsernameFromToken(Mockito.anyString())).thenReturn("admin");
        when(accountService.getAllAccounts()).thenReturn(List.of(test_from_acc, test_to_acc));


        mvc.perform(get("/api/admin/allAccounts")
                .header("Authorization", "admin.jw1.t0k3n"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].account_no", is(test_from_acc.getAccount_no())))
                .andExpect(jsonPath("$[1].account_no", is(test_to_acc.getAccount_no())))
        ;

    }

    @Test
    public void AdminController_GetAccounts_Unauthorized() throws Exception {

        AccountResponseDTO ac1 = new AccountResponseDTO(test_from_acc);
        AccountResponseDTO ac2 = new AccountResponseDTO(test_to_acc);

        when(jwtGen.getUsernameFromToken(Mockito.anyString())).thenReturn("notadmin");
        when(accountService.getAllAccounts()).thenReturn(List.of(test_from_acc, test_to_acc));


        mvc.perform(get("/api/admin/allAccounts")
                        .header("Authorization", "admin.jw1.t0k3n"))
                .andExpect(status().isForbidden())
        ;

    }

    @Test
    public void AdminController_SetStatus() throws Exception {
        Account ac1 = test_from_acc;
        ac1.setStatus(false);
        StatusDTO statusDTO = new StatusDTO(false, 1);

        when(jwtGen.getUsernameFromToken(Mockito.anyString())).thenReturn("admin");
        when(accountService.setAccountStatus(Mockito.any(StatusDTO.class))).thenReturn(ac1);

        MvcResult resp = mvc.perform(
                post("/api/admin/setStatus")
                        .content(asJson(statusDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "jwt.tok3n.admin")
        )
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("Status updated to false", resp.getResponse().getContentAsString());

    }


}
