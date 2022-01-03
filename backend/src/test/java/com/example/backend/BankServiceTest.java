package com.example.backend;

import com.example.backend.bank.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankServiceTest {

    @MockBean
    private BankRepository bankRepository;

    @Autowired
    private BankService bankService;

    @Test
    public void getBanks_shouldReturnListOfBanks() {
        //arrange
        List<Bank> banks = new ArrayList<>();
        banks.add(buildBank(1));
        banks.add(buildBank(2));
        given(bankRepository.findAll()).willReturn(banks);

        //act
        ListOfBanks listOfBanks = bankService.getBanks();

        //assert
        assertEquals("bank1", listOfBanks.getBanks().get(0).getName());
    }

    @Test
    public void addBank_shouldReturnTheNewBank() {
        //arrange
        Bank bank = buildBank(3);
        given(bankRepository.save(any(Bank.class))).willReturn(bank);

        //act
        Bank newBank = bankService.addBank(bank);

        //assert
        assertEquals("bank3", newBank.getName());
    }

    @Test
    public void updateBank_shouldReturnTheUpdatedBank() {
        //arrange
        Bank bank = buildBank(3);
        Long id = 33L;
        bank.setId(id);
        given(bankRepository.findById(id)).willReturn(Optional.of(bank));
        given(bankRepository.save(any(Bank.class))).willReturn(bank);

        //act
        bankService.updateBank(bank);

        //assert
        assertEquals("bank3", bank.getName());
    }

    @Test(expected = BankNotFoundException.class)
    public void updateBank_shouldThrowException_ifItIsNotFound() {
        //arrange
        given(bankRepository.findById(3L)).willReturn(Optional.empty());

        //act
        bankService.updateBank(buildBank(3));
    }

    @Test(expected = BankNotFoundException.class)
    public void deleteBank_shouldThrowException_ifItIsNotFound() {
        //arrange
        Long id = 33L;
        given(bankRepository.findById(id)).willReturn(Optional.empty());

        //act
        bankService.deleteBank(id);
    }

    private Bank buildBank(int number) {
        return new Bank("bank" + number, "Italy", "+43 56487" + number, (long) (3 + number));
    }

}
