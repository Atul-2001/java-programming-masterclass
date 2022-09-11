package com.signature;

public class Main {

    public static void main(String[] args) {

        double myval = 20.00;
        double myval2 = 80.00;

        double res = myval + myval2 * 100.00;
        System.out.println(" Res : " + res);
        double rem = res % 40.00;
        System.out.println("Rem : " + rem);

        boolean status = (rem == 0) ? true : false;

        System.out.println("Status : " + status);

        if (!status) {
            System.out.println("Got some remainder.");
        } else {
            System.out.println("Haven't got any remainder.");
        }
    }
}
