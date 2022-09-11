package com.signature;

import com.signature.Math.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("Sum of n numbers :-");
        for (int i = 0; i <= 10; i++) {
            System.out.print(Series.nSum(i) + " ");
        }

        System.out.println("\nFactorial of n :-");
        for (int i = 0; i <= 10; i++) {
            System.out.print(Series.factorial(i) + " ");
        }

        System.out.println("\nFibonacci of nth place :-");
        for (int i = 0; i <= 10; i++) {
            System.out.print(Series.fibonacci(i) + " ");
        }
    }
}
