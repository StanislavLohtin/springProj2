package com.example.backend;

import com.example.backend.bank.Bank;
import com.example.backend.bank.BankController;
import com.example.backend.bank.BankService;
import com.example.backend.bank.ListOfBanks;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BankController.class)
public class BankControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankService bankService;

    @Test
    public void getBanks_shouldBeSuccessful() throws Exception {
        //assert
        mockMvc.perform(MockMvcRequestBuilders.get("/banks")).andExpect(status().isOk());
    }

    @Test
    public void getBanks_shouldReturnListOfBanks() throws Exception {
        //arrange
        List<Bank> banks = new ArrayList<>();
        banks.add(buildBank(1));
        banks.add(buildBank(2));
        ListOfBanks listOfBanks = new ListOfBanks(banks);
        given(bankService.getBanks()).willReturn(listOfBanks);

        //assert
        mockMvc.perform(MockMvcRequestBuilders.get("/banks"))
                .andExpect(jsonPath("banks").isArray())
                .andExpect(jsonPath("banks[0].name").value("bank1"))
                .andExpect(jsonPath("banks[1].name").value("bank2"));
    }

    @Test
    public void addBank_shouldBeSuccessful() throws Exception {
        //assert
        mockMvc.perform(MockMvcRequestBuilders.post("/banks")
                        .content(asJsonString(buildBank(1)))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void addBank_shouldReturnNewBank() throws Exception {
        //arrange
        Bank bank = buildBank(1);
        given(bankService.addBank(any(Bank.class))).willReturn(bank);

        //assert
        mockMvc.perform(MockMvcRequestBuilders.post("/banks")
                        .content(asJsonString(bank))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(jsonPath("name").value("bank1"));
    }

    @Test
    public void updateBank_shouldReturnNewBank() throws Exception {
        //arrange
        Bank bank = buildBank(1);
        given(bankService.updateBank(any(Bank.class))).willReturn(bank);

        //assert
        mockMvc.perform(MockMvcRequestBuilders.put("/banks")
                        .content(asJsonString(bank))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(jsonPath("name").value("bank1"));
    }

    @Test
    public void deleteBank_shouldBeSuccessful() throws Exception {
        //arrange
        Bank bank = buildBank(1);
        Long id = 33L;
        bank.setId(id);

        //assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/banks/" + id)
                        .content(asJsonString(bank))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private Bank buildBank(int number) {
        return new Bank("bank" + number, "Italy", "+43 56487" + number, (long) (3 + number));
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
