package com.signature;

import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
    private static Scanner input = new Scanner(System.in);
    ArrayList<BankBranch> branch;

    public Bank() {
        this.branch = new ArrayList<BankBranch>();
    }

    public boolean createBranch(String branchName) {

        if (findBranch(branchName) > -1) {
            System.out.println("Branch " + branchName + " already exist !");
            return false;
        } else {
            branch.add(new BankBranch(branchName));
            return true;
        }
    }

    public BankBranch getBranch(String branchName) {
        int index = findBranch(branchName);

        if (index > -1) {
            return branch.get(index);
        } else {
            return null;
        }
    }

    private int findBranch(String branchName) {
        for (int i = 0; i < branch.size(); i++) {
            BankBranch test = branch.get(i);
            if (test.getBranchName().equals(branchName)) {
                return i;
            }
        }
        return -1;
    }

    public void printBranchList() {
        System.out.println("\n====List of all branches====\n");
        for (int i = 0; i < branch.size(); i++) {
            BankBranch getBranch = branch.get(i);
            System.out.println((i+1) + ". " + getBranch.getBranchName());
        }
    }

    public boolean createTransaction() {
        System.out.print("Enter Sender Branch Name : ");
        String branchS = input.nextLine();

        int indexS = findBranch(branchS);
        if (indexS > -1) {
            BankBranch senderB = branch.get(indexS);

            System.out.print("Enter the sender name : ");
            String sender = input.nextLine();

            indexS = senderB.findCustomer(sender);
            if (indexS > -1) {
                Customer senderC = senderB.getCustomer(indexS);

                System.out.print("Enter the amount for transaction : ");
                double amount = input.nextDouble();
                input.nextLine();

                System.out.print("Enter Receiver Branch Name : ");
                String branchR = input.nextLine();

                int indexR = findBranch(branchR);
                if (indexR > -1) {
                    BankBranch receiverB = branch.get(indexR);

                    System.out.print("Enter the receiver name : ");
                    String receiver = input.nextLine();

                    indexR = receiverB.findCustomer(receiver);
                    if (indexR > -1) {
                        Customer receiverC = receiverB.getCustomer(indexR);

                        if (senderC.getBalance() <= 0) {
                            System.out.println("Low on balance !");
                            return false;
                        } else if ((senderC.getBalance() - amount) < 0) {
                            System.out.println("Insufficient Balance !");
                            return false;
                        } else {
                            senderC.setBalance(senderC.getBalance() - amount);
                            senderC.setTransaction(amount);
                            receiverC.setTransaction(amount);
                            receiverC.setBalance(receiverC.getBalance() + amount);
                            return true;
                        }
                    } else {
                        System.out.println("Branch > Receiver does not exist !");
                        return false;
                    }
                } else {
                    System.out.println("Bank > Branch of receiver does not exist !");
                    return false;
                }
            } else {
                System.out.println("Branch > Sender does not exist !");
                return false;
            }
        } else {
            System.out.println("Bank > Sender branch does not exist !");
            return false;
        }
    }
}
