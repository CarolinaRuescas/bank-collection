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

    // Dado un iban y una cantidad, ingresar la cantidad en la cuenta.
    // Si no existe la cuenta muestra en la consola el mensaje "No existe la cuenta".

    public boolean deposit(String iban, double amount){
        if(accountsByIban.containsKey(iban)){
            accountsByIban.get(iban).deposit(amount);
            return true;
        }else{
            System.out.println("No se ha encontado la cuenta");
            return false;
        }
    }

    // Dado un nif, devolver todas las cuentas de ese cliente. Si el cliente no existe devuelve null.









}
