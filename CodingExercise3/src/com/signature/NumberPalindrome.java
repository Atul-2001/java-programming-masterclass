package com.signature;

public class NumberPalindrome {

    public static boolean isPalindrome(int number)
    {
        if(number < 0)
        {
            number = -number;
        }

        int reverse = 0,digit,temp = number;

        while (temp!=0)
        {
            digit = temp%10;
            reverse = (reverse * 10) + digit;
            temp/=10;
        }

        if (number == reverse)
            return true;
        else
            return false;
    }
}
