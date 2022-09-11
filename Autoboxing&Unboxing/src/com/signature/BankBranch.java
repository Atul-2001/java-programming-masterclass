package com.signature;

import java.util.ArrayList;
import java.util.Scanner;


public class BankBranch {

    private static Scanner input = new Scanner(System.in);
    private String branchName;
    private ArrayList<Customer> customer;

    public BankBranch(String name) {
        this.branchName = name;
        this.customer = new ArrayList<Customer>();
    }

    public String getBranchName() {
        return branchName;
    }

    public boolean addCustomer() {
        System.out.print("Enter Customer name : ");
        String name = input.nextLine();

        System.out.print("Add balance to the account : ");
        double balance = input.nextDouble();
        input.nextLine();

        if (findCustomer(name) > -1) {
            System.out.println("Customer with name " + name + " already exist !");
            return false;
        } else {
            customer.add(Customer.addCustomer(name,balance));
            return true;
        }
    }

    protected int findCustomer(String name) {

        for (int i = 0; i < customer.size(); i++) {
            Customer test = customer.get(i);
            if (test.getName().equals(name)) {
                return i;
            }
        }
         return -1;
    }

    public void printDetailOfAll() {
        System.out.println("==========Branch : " + this.branchName + " ==========\n");

        for (int i = 0; i < customer.size(); i++) {
            Customer printDetail = customer.get(i);
            System.out.println("Account Holder  : " + printDetail.getName());
            System.out.println("Account Balance : " + printDetail.getBalance());
            System.out.println("All Transactions :- ");

            for (int j = 0; j < printDetail.getTransaction().size(); j++) {
                System.out.println((j+1) + ". " + printDetail.getTransaction().get(j));
            }
        }
    }

    public Customer getCustomer(int index) {
        if (index > -1) {
            return customer.get(index);
        } else {
            return null;
        }
    }

}
