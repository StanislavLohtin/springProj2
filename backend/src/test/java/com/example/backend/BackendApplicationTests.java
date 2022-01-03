package com.example.backend;

import com.example.backend.bank.ListOfBanks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BackendApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getBanks_shouldBeSuccessful() {
        //act
        ResponseEntity<ListOfBanks> response = restTemplate.getForEntity("/banks", ListOfBanks.class);

        //assert
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

}
