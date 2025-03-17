package org.ies.bank;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

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

    //metodo para buscar clientes
    public Customer findCustomer(String nif){
        for (Customer customer : customers){
            if(customer.getNif().equals(nif)){
                return customer;
            }
        }
        return null;
    }
    // metodo que devuelve un booleano si existe la cuenta
    // este metodo lo que hace es dejar el siguente más limpio
    public boolean existsCustomer(String nif){
        return findCustomer(nif)!= null;
    }

    // Dado un nif, devolver todas las cuentas de ese cliente. Si el cliente no existe devuelve null.
    public List<Account> findCustomerAccounts(String nif){
       if(existsCustomer(nif)) {
           List<Account> accountCustomer = new ArrayList<>();
           for (var account : accountsByIban.values()) {
               if (account.getNif().equals(nif)) {
                   accountCustomer.add(account);
               }
           }
           return accountCustomer;
       }else {
           return null;
       }
    }

    // Dado un iban y una cantidad, saca la antidad en la cuenta, si no hay suficiente saldo muestra el mensaje
    // "No hay saldo suficiente". Si no existe la cuenta muestra en la ocnsola el mensaje "No existe la cuenta"
    public boolean withdraw(String iban, double amount) {
        if (accountsByIban.containsKey(iban)) {
            var account = accountsByIban.get(iban);

            if (!account.withdraw(amount)) {
                System.out.println("no hay saldo suficiente");
                return false;
            } else {
                System.out.println("Se ha sacado el dinero correctamente");
                return true;
            }
        } else {
            System.out.println("No existe la cuenta");
            return false;
        }
    }

    // Realizar una transferencia entre dos cuentas de dos clientes. Para realizar la transerencia será necesario
    // proporcionar la cantidad, el iban de cuenta de origen y de destino. Si no existe la cuenta origen muestra
    // "No existe la cuenta origen", si no existe la cuenta destino "No existe la cuenta destino", si no hay saldo
    // suficiente en la cuenta de origen muestra "No hay saldo suficiente en origen".
    public boolean transfer(String originIban,String destinationIban, double amount){
        if(accountsByIban.containsKey(originIban)){
            var origin = accountsByIban.get(originIban);
            if(accountsByIban.containsKey(destinationIban)){
                var destination = accountsByIban.get(destinationIban);
                if(origin.withdraw(amount)){
                    destination.deposit(amount);
                    return true;
                }else{
                    System.out.println("No hay suficiente saldo");
                }
            }else{
                System.out.println("No existe la cuenta de destino");
            }
        }else{
            System.out.println("no existe la cuenta de origen");
        }
        return false;
    }


    //Metodo para poder filtrar los nifs
    public Set<String> findZipCodeCustomerNifs(int zipCode){
        Set<String >  nifs = new HashSet<>();
        for(var customer : customers){
            if(customer.getZipCode() == zipCode){
                nifs.add(customer.getNif());
            }
        }
        return nifs;
    }

    // Dado un codigo postal, devuelve todas las cuentas cuyo propietario vive en ese codigo postal
    public List<Account> findZipCodeAccounts(int zipCode){
        var nifs = findZipCodeCustomerNifs(zipCode);
        var zipCodeAccounts = new ArrayList<Account>();
        for(var account : accountsByIban.values()){
            if(nifs.contains((account.getNif()))){
                zipCodeAccounts.add(account);
            }
        }
        return zipCodeAccounts;
    }









}
