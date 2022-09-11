package com.signature;

import java.util.Scanner;

public class Main {

    private static Scanner input = new Scanner(System.in);
    private static Bank bank = new Bank();

    public static void main(String[] args) {
        boolean exit = false;
        printHeader();
        printMenu();

        while (!exit) {

            System.out.print("\nEnter your option (0 for all options) : ");
            int op = input.nextInt();
            input.nextLine();

            switch (op) {
                case 0:
                    printMenu();
                    break;

                case 1:
                    createBranch();
                    break;

                case 2:
                    addCustomerInBranch();
                    break;

                case 3:
                    createTransaction();
                    break;

                case 4:
                    bank.printBranchList();
                    break;

                case 5:
                    branchDetail();
                    break;

                case 6:
                    exit = true;
                    break;

                default:
                    System.out.println("Choose correct option !");
            }
        }
    }

    public static void printHeader() {
        System.out.println("\n*-*-*-*-*- Signature Bank -*-*-*-*-*-*\n\n");
    }

    public static void printMenu() {
        System.out.println("Choose Option :- ");
        System.out.println("0. Print Menu");
        System.out.println("1. Create Branch");
        System.out.println("2. Add customer to the branch");
        System.out.println("3. Create transaction for customer of a branch");
        System.out.println("4. List all branch");
        System.out.println("5. Detail of branch");
        System.out.println("6. Exit");
    }

    public static void createBranch() {
        System.out.print("\nEnter Branch Name : ");
        if (bank.createBranch(input.nextLine())) {
            System.out.println("New branch added successfully !!");
        } else {
            System.out.println("Failed to add branch !");
        }
    }

    public static void addCustomerInBranch() {
        System.out.print("Enter Branch of New Customer : ");
        String branchName = input.nextLine();

        BankBranch branch = bank.getBranch(branchName);
        if (branch != null) {
            if (branch.addCustomer()) {
                System.out.println("Branch : " + branch.getBranchName() + " > New Customer added in branch !!!");
            } else {
                System.out.println("Branch : " + branch.getBranchName() + " > Error in adding new customer !");
            }
        } else {
            System.out.println("Bank : > Branch " + branchName + " does not exist !");
        }
    }

    public static void createTransaction() {
        if (bank.createTransaction()) {
            System.out.println("Bank : > Transaction Completed Successfully !!!");
        } else {
            System.out.println("Bank : > Transaction Failed !");
        }
    }

    public static void branchDetail() {
        System.out.print("Enter Branch Name : ");
        String branchName = input.nextLine();

        BankBranch branch = bank.getBranch(branchName);
        if (branch != null) {
            branch.printDetailOfAll();
        } else {
            System.out.println("Bank : > Branch " + branchName + " does not exist !");
        }
    }
}
