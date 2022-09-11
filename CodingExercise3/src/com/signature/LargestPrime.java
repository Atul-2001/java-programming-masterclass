package com.signature;

public class LargestPrime {
    public static int getLargestPrime(int number) {
        if (number < 2) {
            return -1;
        } else if (number < 4) {
            return number;
        } else {
            int i, large = 1, largest = 1, prev;

            for (i = 2; i < number; i++) {
                if (number % i == 0) {
                    break;
                }
            }

            if (i == number) {
                return number;
            } else {
                for (i = 1; i < number; i++) {
                    if (number % i == 0) {
                        large = i;
                    }
                }
                for (i = 2; i < large; i++) {
                    if (large % i == 0) {
                        break;
                    }
                }
                if (i == large) {
                    largest = large;
                } else {
                    while (true) {
                        for (i = 2; i < large; i++) {
                            if (large % i == 0) {
                                largest = i;
                            }
                        }

                        if ((largest > 2 && largest%2 == 0) || (largest > 3 && largest%3 == 0) || (largest > 5 && largest%5 == 0) || (largest > 7 && largest%7 == 0)) {
                            large = largest;
                        }
                        else {
                            break;
                        }
                    }
                }
            }
            return largest;
        }
    }
}
