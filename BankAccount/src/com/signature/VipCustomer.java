package com.signature;

public class VipCustomer {
    private String name;
    private int creditLimit;
    private String emailAddress;

    public VipCustomer() {
        this("Default",1000,"default@bank.com");
        System.out.println("Empty Constructor Called.");
    }

    public VipCustomer(String name, String emailAddress) {
        this(name,5000,emailAddress);
        System.out.println("Partial Constructor Called.");
    }

    public VipCustomer(String name, int creditLimit, String emailAddress) {
        System.out.println("Constructor Called.");
        this.name = name;
        this.creditLimit = creditLimit;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
