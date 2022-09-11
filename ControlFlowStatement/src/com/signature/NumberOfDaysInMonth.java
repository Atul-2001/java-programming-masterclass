package com.signature;

public class NumberOfDaysInMonth {

    public static boolean isLeapYear(int year)
    {
        if (year < 1 || year > 9999)
        {
            return false;
        }
        else
        {
            if(year%4 == 0)
            {
                if (year%100 == 0)
                {
                    if (year%400 == 0)
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
    }

    public static int getDaysInMonth(int month, int year)
    {
        if ((month < 1 || month > 12) || (year < 1 || year > 9999))
        {
            return -1;
        }
        else
        {
            int days = 0;

            if (month <= 7 && month%2 == 0)
            {
                days = 30;
            }
            else if (month <= 7 && month%2 != 0)
            {
                days = 31;
            }
            else if (month > 7 && month%2 == 0)
            {
                days = 31;
            }
            else if (month > 7 && month%2 != 0)
            {
                days = 30;
            }

            if(isLeapYear(year) && month == 2)
            {
                days = 29;
            }
            else if (!isLeapYear(year) && month == 2)
            {
                days = 28;
            }

            return days;
        }
    }
}
