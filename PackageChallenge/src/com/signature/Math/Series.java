package com.signature.Math;

public class Series {

    public static int nSum(int n) {
        if (n < 0) {
            System.out.println("Invalid Number!");
            return -1;
        } else {
            int sum = 0;
            for(int i = 0; i <= n; i++) {
                sum += i;
            }
            return sum;
        }
    }

    public static int factorial(int n) {
        if (n < 0) {
            System.out.println("Invalid Number!");
            return -1;
        } else if (n == 0) {
            return 0;
        } else {
            int fact = 1;
            for (int i = n; i > 0; i--) {
                fact *= i;
            }
            return fact;
        }
    }

    public static int fibonacci(int n) {
        if (n < 0) {
            System.out.println("Invalid Value!");
            return -1;
        } else {
            int first, second, res = 0;
            first = -1;
            second = 1;

            for (int i = 0; i <= n; i++) {
                res = first + second;
                first = second;
                second = res;
            }
            return res;
        }
    }
}
