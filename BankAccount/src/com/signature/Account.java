package com.signature;

public class Account {

    private String accountNumber;
    private double balance;
    private String Name;
    private String Email;
    private String Phone;

    public Account() {
        this("8254000100047001",1000,"Default","default@bank.com","0000000000");
        System.out.println("Empty Constructor Called");
    }

    public Account(String accountNumber, double balance, String name, String email, String phone) {
        System.out.println("Full constructor Called");
        this.accountNumber = accountNumber;
        this.balance = balance;
        Name = name;
        Email = email;
        Phone = phone;
    }

    public Account(String name, String email, String phone) {
        this("8254000100047002",1200,name,email,phone);
        System.out.println("Partial Constructor Called.");
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void deposit(int amount) {
        if (amount < 0) {
            System.out.println("Invalid Value! Please enter correct amount.");
        } else {
            balance += amount;
            System.out.println("Deposit of "+amount+" made successfully.\nNew Balance is "+balance);
        }
    }

    public void withdraw(int amount) {
        if (amount < 0) {
            System.out.println("Invalid Value! Please enter correct amount to withdraw.");
        } else if (balance < 0) {
            System.out.println("Insufficient balance to withdraw!");
        } else if (balance-amount < 0){
            System.out.println("Only "+balance+" left. Withdraw not possible.");
        } else {
            balance -= amount;
            System.out.println("Withdraw of "+amount+" processed. Remaining balance is "+balance);
        }
    }
}
