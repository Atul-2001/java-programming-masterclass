package com.signature;

public class Main {

    public static void main(String[] args) {
        BankAccount account = new BankAccount("Atul Suryavanshi", "8254000100047829", 100.00);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread 1 Balance : $" + account.getBalance() + " | Deposited : $300.00 | Current Balance : $" + account.deposit(300.00));
                System.out.println("Thread 1 Balance : $" + account.getBalance() + " | Withdrawn : $50.00  | Current Balance : $" + account.withdraw(50.00));
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread 2 Balance : $" + account.getBalance() + " | Deposited : $203.75 | Current Balance : $" + account.deposit(203.75));
                System.out.println("Thread 2 Balance : $" + account.getBalance() + " | Withdrawn : $100.00 | Current Balance : $" + account.withdraw(100.00));
            }
        }).start();
    }
}
