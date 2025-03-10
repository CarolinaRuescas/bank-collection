package org.ies.bank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Customer {
    private String nif;
    private String name;
    private String surname;
    private int zipCode;
}
