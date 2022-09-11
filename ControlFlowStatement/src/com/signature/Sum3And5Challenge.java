package com.signature;

public class Sum3And5Challenge {

    public static void main(String[] args) {

        int sum = 0, count = 0;
        for (int i = 1; i <= 1000; i++)
        {
            if((i%3 == 0 && i%5 == 0) && count < 5)
            {
                ++count;
                System.out.println(i);
                sum+=i;
            }
        }

        System.out.println("Sum of 5 numbers divisible by both 3 and 5 are : "+sum);
    }
}
