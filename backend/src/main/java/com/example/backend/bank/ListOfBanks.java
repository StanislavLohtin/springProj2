package com.example.backend.bank;

import java.util.ArrayList;
import java.util.List;

public class ListOfBanks {
    private List<Bank> banks;

    public ListOfBanks() {
        this.banks = new ArrayList<>();
    }

    public ListOfBanks(List<Bank> banks) {
        this.banks = banks;
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }
}
