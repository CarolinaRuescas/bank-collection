package org.ies.bank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
    private String iban;
    private String nif;
    private double balance;

    public void deposit(double amount){
        balance += amount;
    }
}
