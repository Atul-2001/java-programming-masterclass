package com.signature;

public class SumDigitChallenge {

    public static int sumDigit(int number)
    {
        if(number < 10)
        {
            return -1;
        }
        else
        {
            int digit, sum = 0;

            while (number!=0)
            {
                digit = number%10;
                sum+=digit;
                number/=10;
            }

            return sum;
        }
    }
}
