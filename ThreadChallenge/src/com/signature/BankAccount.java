package com.signature;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private String AccountHolderName;
    private String accountNo;
    private volatile double balance;
    private Lock lock;

    public BankAccount(String accountHolderName, String accountNo, double balance) {
        AccountHolderName = accountHolderName;
        this.accountNo = accountNo;
        this.balance = balance;
        lock = new ReentrantLock();
    }

    public double getBalance() {
        return balance;
    }

//    public synchronized double deposit(double amount) {
//        balance += amount;
//        return balance;
//    }
//
//    public synchronized  double withdraw(double amount) {
//        balance -= amount;
//        return balance;
//    }

//    public double deposit(double amount) {
//        lock.lock();
//        try {
//            balance += amount;
//        } finally {
//            lock.unlock();
//        }
//        return balance;
//    }
//
//    public double withdraw(double amount) {
//        lock.lock();
//        try {
//            balance -= amount;
//        } finally {
//            lock.unlock();
//        }
//        return balance;
//    }

    public double deposit(double amount) {
        boolean status = false;
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                try {
                    balance += amount;
                    status = true; // Status variable is thread safe because it is a local variable and local variables are made in thread stack and does not interfere with other thread.
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Deposit Can't get the lock.");
            }
            System.out.println("Transaction status : " + status);
            return balance;
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            System.out.println("Transaction status : " + status);
            return balance;
        }
    }

    public double withdraw(double amount) {
        boolean status = false;
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                try {
                    balance -= amount;
                    status = true; // Status variable is thread safe because it is a local variable and local variables are made in thread stack and does not interfere with other thread.
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Withdraw Can't get the lock.");
            }
            System.out.println("Transaction status : " + status);
            return balance;
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            System.out.println("Transaction status : " + status);
            return balance;
        }
    }

    public String getAccountHolderName() {
        return AccountHolderName;
    }

    public void printAccountNo() {
        System.out.println("Account No : " + accountNo);
    }
}
