package com.signature;

public class BankAccount {
    private String AccountNo;
    private double balance;
    public static final int SAVING = 1;
    public static final int CURRENT = 2;

    private int accountType;

    public BankAccount(String accountNo, double balance, int accountType) {
        AccountNo = accountNo;
        this.balance = balance;
        this.accountType = accountType;
    }

    public double deposit(double amount, boolean branch) {
        balance += amount;
        return balance;
    }

    public double withdraw(double amount, boolean branch) {
        if ((amount > 500.00) && !branch) {
            throw  new IllegalArgumentException("Can't withdraw large amount from ATM");
        }
        balance -= amount;
        return balance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isCurrent() {
        return accountType == CURRENT;
    }
}
