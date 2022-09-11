package com.signature;

import java.util.ArrayList;

public class Customer {

    private String name;
    private double balance;
    private ArrayList<Double> transaction = new ArrayList<Double>();

    private Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
        setTransaction(balance);
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Double> getTransaction() {
        return transaction;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setTransaction(double amount) {
        transaction.add(amount);
    }

    public static Customer addCustomer(String name, double balance) {
        return new Customer(name, balance);
    }
}
