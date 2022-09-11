package com.signature;

public class GreatestCommonDivisor {

    public static int getGreatestCommonDivisor(int first, int second)
    {
        if (first < 10 || second < 10) {
            return -1;
        }
        else {
            int GCD = 0;

            for (int i = 1; i < first/2 || i < second/2; i++)
            {
                if ((first%i == 0) && (second%i == 0))
                {
                    GCD = i;
                }
            }

            return GCD;
        }
    }
}
