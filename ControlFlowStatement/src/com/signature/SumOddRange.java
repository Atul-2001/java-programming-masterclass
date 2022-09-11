package com.signature;

public class SumOddRange {

    public static boolean isOdd(int number)
    {
        if (number < 1)
        {
            return false;
        }
        else
        {
            if (number%2 != 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public static int sumOdd(int start, int end)
    {
        if((start < 1 || end < 1) || start > end )
        {
            return -1;
        }
        else
        {
            int sum = 0;
            for (int i = start; i <= end; i++)
            {
                if (isOdd(i))
                {
                    sum+=i;
                }
            }
            return sum;
        }
    }
}
