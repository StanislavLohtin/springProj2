package com.example.backend.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class BankConfig {

    private final BankRepository bankRepository;

    @Autowired
    public BankConfig(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            Bank bank1 = new Bank("bank1", "Italy", "+42 (233) 98333", 3L);
            Bank bank2 = new Bank("bank2", "France", "+32 (233) 25233", 5L);
            List<Bank> banks = new ArrayList<>();
            banks.add(bank1);
            banks.add(bank2);
            bankRepository.saveAll(banks);
        };
    }

}
