package com.example.banking_project_group2.service;

import com.example.banking_project_group2.dto.TransactionsDTO;
import com.example.banking_project_group2.dto.TransactionsResponseDTO;
import com.example.banking_project_group2.dto.WithdrawalDTO;
import com.example.banking_project_group2.exceptions.BalanceExceptions;
import com.example.banking_project_group2.model.Account;
import com.example.banking_project_group2.model.Customer;
import com.example.banking_project_group2.model.Transactions;
import com.example.banking_project_group2.repository.AccountRepository;
import com.example.banking_project_group2.repository.TransactionsRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionsRepo transactionsRepo;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private TransactionsImplementation transactionsService;

    Customer test_from_cust;
    Customer test_to_cust;
    Account test_from_acc;
    Account test_to_acc;

    @BeforeEach
    public void init() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        test_from_cust = new Customer(1, "test_from_cust", "password", 20);
        test_from_acc = new Account(1, "Savings", 2000, "Test",
                "One", "Test add", "123456789012", "Test Occ",
                "test1@mail.com", "1234567890", dateFormat.parse("29/04/2001"), test_from_cust);


        test_to_cust = new Customer(2, "test_to_cust", "password", 20);
        test_to_acc = new Account(2, "Salary", 2000, "Test",
                "Two", "Test add", "123456789012", "Test Occ",
                "test2@mail.com", "1234567890", dateFormat.parse("29/04/2001"), test_to_cust);


    }
    @Test
    public void TransactionService_saveTransaction_Saved(){

        TransactionsDTO transactionsDTO = new TransactionsDTO(200, 1, 2);

        Account from = test_from_acc;
        Account to = test_to_acc;
        from.setStatus(true);
        to.setStatus(true);
        Date currDate = new Date();
        Transactions transactions = new Transactions(1, currDate, 200, test_from_acc, test_to_acc, true);
        when(accountRepository.findById(1)).thenReturn(from);
        when(accountRepository.findById(2)).thenReturn(to);
        when(transactionsRepo.save(Mockito.any(Transactions.class))).thenReturn(transactions);

        Transactions getT;
        try {
            getT = transactionsService.saveTransaction(transactionsDTO);
        } catch (BalanceExceptions e) {
            throw new RuntimeException(e);
        }

        assertNotNull(getT);
        assertEquals(getT.getTransaction_id(), 1);
        assertEquals(getT.getAmount(), 200);
        assertEquals(from.getBalance(), 1800);
        assertEquals(to.getBalance(), 2200);


    }


    @Test
    public void TransactionService_saveTransaction_InsufficientBalance(){

        TransactionsDTO transactionsDTO = new TransactionsDTO(20000, 1, 2);

        Account from = test_from_acc;
        Account to = test_to_acc;
        from.setStatus(true);
        to.setStatus(true);
        Date currDate = new Date();
        when(accountRepository.findById(1)).thenReturn(from);
        when(accountRepository.findById(2)).thenReturn(to);
        assertThatThrownBy(() -> transactionsService.saveTransaction(transactionsDTO))
                .isInstanceOf(BalanceExceptions.class);
    }

    @Test
    public void TransactionService_saveTransaction_InactiveFromAccount(){

        TransactionsDTO transactionsDTO = new TransactionsDTO(200, 1, 2);

        Account from = test_from_acc;
        Account to = test_to_acc;
        from.setStatus(false);
        to.setStatus(true);
        Date currDate = new Date();
        when(accountRepository.findById(1)).thenReturn(from);
        when(accountRepository.findById(2)).thenReturn(to);
        assertThatThrownBy(() -> transactionsService.saveTransaction(transactionsDTO))
                .isInstanceOf(BalanceExceptions.class);
    }

    @Test
    public void TransactionService_saveTransaction_InactiveToAccount(){

        TransactionsDTO transactionsDTO = new TransactionsDTO(200, 1, 2);

        Account from = test_from_acc;
        Account to = test_to_acc;
        from.setStatus(true);
        to.setStatus(false);
        Date currDate = new Date();
        when(accountRepository.findById(1)).thenReturn(from);
        when(accountRepository.findById(2)).thenReturn(to);
        assertThatThrownBy(() -> transactionsService.saveTransaction(transactionsDTO))
                .isInstanceOf(BalanceExceptions.class);
    }

    @Test
    public void TransactionService_saveTransaction_SameAccount(){

        TransactionsDTO transactionsDTO = new TransactionsDTO(200, 1, 2);

        Account from = test_from_acc;
        Account to = test_to_acc;
        from.setStatus(true);
        to.setStatus(true);
        Date currDate = new Date();
        when(accountRepository.findById(1)).thenReturn(from);
        when(accountRepository.findById(2)).thenReturn(from);
        assertThatThrownBy(() -> transactionsService.saveTransaction(transactionsDTO))
                .isInstanceOf(BalanceExceptions.class);
    }

    @Test
    public void TransactionService_saveTransaction_NegativeAmount(){

        TransactionsDTO transactionsDTO = new TransactionsDTO(-20, 1, 2);

        Account from = test_from_acc;
        Account to = test_to_acc;
        from.setStatus(true);
        to.setStatus(true);
        Date currDate = new Date();
        when(accountRepository.findById(1)).thenReturn(from);
        when(accountRepository.findById(2)).thenReturn(to);
        assertThatThrownBy(() -> transactionsService.saveTransaction(transactionsDTO))
                .isInstanceOf(BalanceExceptions.class);
    }


    @Test
    public void TransactionService_getTransactions(){
        List<Transactions> to_trans = new ArrayList<>();
        List<Transactions> from_trans = new ArrayList<>();

        Account ac1 = test_from_acc;
        Account ac2 = test_to_acc;
        ac1.setStatus(true);
        ac2.setStatus(true);
        from_trans.add(new Transactions(1, new Date(), 200, ac1, ac2, true));
        from_trans.add(new Transactions(2, new Date(), 100, ac1, ac2, true));
        from_trans.add(new Transactions(3, new Date(), 30000, ac1, ac2, false));

        to_trans.add(new Transactions(4, new Date(), 200, ac2, ac1, true));
        to_trans.add(new Transactions(5, new Date(), 30000, ac2, ac1, false));

        ac1.setTo_transaction(to_trans); ac1.setFrom_transaction(from_trans);
        ac2.setFrom_transaction(to_trans); ac2.setTo_transaction(from_trans);

        when(accountRepository.findById(1)).thenReturn(ac1);

        List<TransactionsResponseDTO> transactionsResponseDTO;
        try {
            transactionsResponseDTO = transactionsService.viewAllTransactions(1);
        } catch (BalanceExceptions e) {
            throw new RuntimeException(e);
        }
        assertEquals(transactionsResponseDTO.size(), 5);

    }

    @Test
    public void TransactionService_getTransactions_Null(){
        List<Transactions> to_trans = new ArrayList<>();
        List<Transactions> from_trans = new ArrayList<>();

        Account ac1 = test_from_acc;
        Account ac2 = test_to_acc;
        ac1.setStatus(true);
        ac2.setStatus(true);

        when(accountRepository.findById(1)).thenReturn(ac1);

        assertThatThrownBy(() -> transactionsService.viewAllTransactions(1))
                .isInstanceOf(BalanceExceptions.class);

    }


    @Test
    public void TransactionService_Withdrawal_Saved(){

        WithdrawalDTO withdrawalDTO = new WithdrawalDTO(200, 1);

        Account from = test_from_acc;

        from.setStatus(true);

        Date currDate = new Date();
        Transactions transactions = new Transactions(1, currDate, 200, test_from_acc, null, true);

        when(accountRepository.findById(1)).thenReturn(from);
        when(transactionsRepo.save(Mockito.any(Transactions.class))).thenReturn(transactions);

        Transactions getT;
        try {
            getT = transactionsService.withdraw(withdrawalDTO);
        } catch (BalanceExceptions e) {
            throw new RuntimeException(e);
        }

        assertNotNull(getT);
        assertEquals(getT.getTransaction_id(), 1);
        assertEquals(getT.getAmount(), 200);
        assertEquals(from.getBalance(), 1800);

    }

    @Test
    public void TransactionService_withdrawal_InactiveAccount(){

        WithdrawalDTO withdrawalDTO = new WithdrawalDTO(200, 1);

        Account from = test_from_acc;

        from.setStatus(false);
        Date currDate = new Date();
        when(accountRepository.findById(1)).thenReturn(from);
        assertThatThrownBy(() -> transactionsService.withdraw(withdrawalDTO))
                .isInstanceOf(BalanceExceptions.class);
    }

    @Test
    public void TransactionService_withdrawal_InsufficientBalance(){

        WithdrawalDTO withdrawalDTO = new WithdrawalDTO(200000, 1);

        Account from = test_from_acc;

        from.setStatus(true);
        Date currDate = new Date();
        when(accountRepository.findById(1)).thenReturn(from);
        assertThatThrownBy(() -> transactionsService.withdraw(withdrawalDTO))
                .isInstanceOf(BalanceExceptions.class);
    }

    @Test
    public void TransactionService_withdrawal_NegativeAmount(){

        WithdrawalDTO withdrawalDTO = new WithdrawalDTO(-20, 1);

        Account from = test_from_acc;

        from.setStatus(true);
        Date currDate = new Date();
        when(accountRepository.findById(1)).thenReturn(from);
        assertThatThrownBy(() -> transactionsService.withdraw(withdrawalDTO))
                .isInstanceOf(BalanceExceptions.class);
    }


}
