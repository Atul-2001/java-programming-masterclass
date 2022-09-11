package com.signature;

import java.util.Scanner;

public class InputCalculator {
    public static void inputThenPrintSumAndAverage() {
        Scanner input = new Scanner(System.in);
        int num = 0, sum = 0, count = 0;
        long avg = 0;

        while (true) {
            boolean isInt = input.hasNextInt();
            if (isInt) {
                num = input.nextInt();
                ++count;
                sum = sum + num;
                avg = Math.round((double)sum/(double)count);
            } else {
                System.out.println("SUM = " + sum + " AVG = " + avg);
                break;
            }
            input.nextLine();
        }
        input.close();
    }
}
