package com.signature;

import java.util.Scanner;

public class MinimumElementChallenge {

    private static Scanner input = new Scanner(System.in);

    public static int[] readIntegers(int count) {
        int[] array = new int[count];

        System.out.println("Enter "+count+" elements in the array :- ");
        for (int i = 0; i < count; i++) {
            array[i] = input.nextInt();
            input.nextLine();
        }

        return array;
    }

    public static void findMin(int[] array) {

        int min = array[0];
        int minIndex = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
                minIndex = i;
            }
        }

        System.out.println("Minimum Value of the array is "+min+" at index "+minIndex);
    }
}
