package com.example.backend.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/banks")
public class BankController {

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping
    public ResponseEntity<ListOfBanks> getBanks() {
        ListOfBanks banks = bankService.getBanks();
        return new ResponseEntity<>(banks, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Bank> addBank(@RequestBody Bank bank) {
        Bank newBank = bankService.addBank(bank);
        return new ResponseEntity<>(newBank, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Bank> updateBank(@RequestBody Bank bank) {
        Bank updatedBank = bankService.updateBank(bank);
        return new ResponseEntity<>(updatedBank, HttpStatus.OK);
    }

    @DeleteMapping("{bankId}")
    public ResponseEntity<Bank> deleteBank(@PathVariable("bankId") Long bankId) {
        bankService.deleteBank(bankId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
