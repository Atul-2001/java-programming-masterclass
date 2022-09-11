package com.signature;

public class MinutesToYearsDaysCalculator {

    public static void printYearsAndDays(long minutes)
    {
        if ( minutes < 0)
        {
            System.out.println("Invalid Value");
        }
        else
        {
            int nDay = (int) (minutes / 1440);
            int years =  nDay / 365;
            int days =   nDay - (years * 365);

            String result = minutes + " min = " + years + " y and " + days + " d";
            System.out.println(result);
        }
    }
}
