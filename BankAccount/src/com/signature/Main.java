package com.signature;

public class Main {

    public static void main(String[] args) {

        Account atul = new Account();
//        atul.setAccountNumber("8254000100047829");
//        atul.setBalance(150000);
//        atul.setName("Atul Suryavanshi");
//        atul.setEmail("atul.atendra15@gmail.com");
//        atul.setPhone("8869975446");

        System.out.println("Account No:- "+atul.getAccountNumber());
        System.out.println("Balance   :- "+atul.getBalance());
        System.out.println("Name      :- "+atul.getName());
        System.out.println("Email     :- "+atul.getEmail());
        System.out.println("Phone     :- "+atul.getPhone());

        atul.withdraw(150001);
        atul.deposit(1200);
        atul.withdraw(1500);
        System.out.println();

        Account rishu = new Account("Rishu Singh","suryavanshirishu27@gmail.com","7054935280");
        System.out.println("Account No:- "+rishu.getAccountNumber());
        System.out.println("Balance   :- "+rishu.getBalance());
        System.out.println("Name      :- "+rishu.getName());
        System.out.println("Email     :- "+rishu.getEmail());
        System.out.println("Phone     :- "+rishu.getPhone());
        System.out.println();

        Account chotu = new Account("8254000100047003",1300,"Ashutosh Suryavanshi","ashutosh02@gmail.com","9935577383");
        System.out.println("Account No:- "+chotu.getAccountNumber());
        System.out.println("Balance   :- "+chotu.getBalance());
        System.out.println("Name      :- "+chotu.getName());
        System.out.println("Email     :- "+chotu.getEmail());
        System.out.println("Phone     :- "+chotu.getPhone());
        System.out.println();


        VipCustomer vip1 =new VipCustomer();
        System.out.println("Name          :- "+vip1.getName());
        System.out.println("Credit Limit  :- "+vip1.getCreditLimit());
        System.out.println("Email Address :- "+vip1.getEmailAddress());
        System.out.println();

        VipCustomer vip2 =new VipCustomer("Atul Suryavanshi","atul.atendra15@gmail.com");
        System.out.println("Name          :- "+vip2.getName());
        System.out.println("Credit Limit  :- "+vip2.getCreditLimit());
        System.out.println("Email Address :- "+vip2.getEmailAddress());
        System.out.println();

        VipCustomer vip3 =new VipCustomer("Rishu Singh",10000,"suryavanshirishu27@gmail.com");
        System.out.println("Name          :- "+vip3.getName());
        System.out.println("Credit Limit  :- "+vip3.getCreditLimit());
        System.out.println("Email Address :- "+vip3.getEmailAddress());
        System.out.println();
    }
}
