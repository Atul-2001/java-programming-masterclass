package com.signature;

public class FirstLastDigitSum {

    public static int sumFirstAndLastDigit(int number)
    {
        if (number < 0)
        {
            return -1;
        }
        else if (number < 10)
        {
            return (number + number);
        }
        else
        {
            int first, last, digit;

            last = number%10;

            while (number >= 10)
            {
                number/=10;
            }
            first = number;

            return (first + last);
        }
    }
}
