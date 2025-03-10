package org.ies.bank;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor

public class Bank {
    private String name;
    private Map<String, Account> accountsByIban;
    private List<Customer> customers;
}
