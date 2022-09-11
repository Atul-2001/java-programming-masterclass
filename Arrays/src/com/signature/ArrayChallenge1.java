package com.signature;

import java.util.Scanner;

public class ArrayChallenge1 {

    private static Scanner input = new Scanner(System.in);

    public static int[] getIntegers(int size) {
        int[] integerArray = new int[size];

        System.out.println("Enter elements in array :- ");

        for (int i = 0; i < size; i++) {
            integerArray[i] = input.nextInt();
        }

        return integerArray;
    }

    public static void sortIntegers(int[] integers) {
        int max;

        for (int i = 0; i < integers.length-1; i++) {
            for (int j = i+1; j < integers.length; j++) {
                if (integers[i] < integers[j]) {
                    max = integers[j];
                    integers[j] = integers[i];
                    integers[i] = max;
                }
            }
        }
    }

    public static void printArray(int[] array) {
        System.out.println("Array in sorted decreasing order :- ");
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
