package com.example.backend.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankService {

    private final BankRepository bankRepository;

    @Autowired
    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public ListOfBanks getBanks() {
        return new ListOfBanks(bankRepository.findAll());
    }

    public Bank addBank(Bank bank) {
        return bankRepository.save(bank);
    }

    public Bank updateBank(Bank bank) {
        Bank bankFromDb = bankRepository.findById(bank.getId()).orElseThrow(BankNotFoundException::new);
        bankFromDb.setName(bank.getName());
        bankFromDb.setCountry(bank.getCountry());
        bankFromDb.setPhone(bank.getPhone());
        bankFromDb.setRank(bank.getRank());
        return bankRepository.save(bankFromDb);
    }

    public void deleteBank(Long bankId) {
        Bank bankFromDb = bankRepository.findById(bankId).orElseThrow(BankNotFoundException::new);
        bankRepository.delete(bankFromDb);
    }
}
