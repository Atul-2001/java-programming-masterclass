package com.signature;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

//        System.out.print("Enter a number :- ");
//        int X = scanner.nextInt();
//        scanner.nextLine();
//
//        for (int x = 1; x <= 12; x++) {
//            System.out.println(X + " X " + x + " = " + X*x);
//        }

        X x = new X(new Scanner(System.in));
        x.x();
    }
}
