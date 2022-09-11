package com.signature;

import java.util.Scanner;

public class ReverseArrayChallenge {

    private static Scanner input = new Scanner(System.in);

    public static int[] reverse(int[] array) {
        int[] revArray = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            revArray[i] = array[array.length - 1 - i];
        }

        return revArray;
    }
}
