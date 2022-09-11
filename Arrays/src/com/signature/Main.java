package com.signature;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
//	    int[] arr = ArrayChallenge1.getIntegers(10);
//		ArrayChallenge1.sortIntegers(arr);
//	    ArrayChallenge1.printArray(arr);

//		int[] array = MinimumElementChallenge.readIntegers(5);
//		MinimumElementChallenge.findMin(array);

        System.out.println("Enter the count : -");
        int count = input.nextInt();
        input.nextLine();

        int[] array = MinimumElementChallenge.readIntegers(count);
        int[] revArray = ReverseArrayChallenge.reverse(array);

        System.out.println("Elements of array : " + Arrays.toString(array));
        System.out.println("Elements of reversed array : " + Arrays.toString(revArray));

    }
}
