package com.signature;

public class NumberToWords {

    public static int reverse(int number)
    {
        int newNumber = number, reverse = 0;
        if (number < 0)
        {
            newNumber = -number;
        }

        while (newNumber!=0)
        {
            reverse = (reverse * 10) + (newNumber%10);
            newNumber/=10;
        }

        if (number < 0)
        {
            reverse = -reverse;
        }

        return reverse;
    }

    public static int getDigitCount(int number)
    {
        if (number < 0)
            return -1;
        else
        {
            int count = 0;

            if (number == 0)
            {
                ++count;
            }
            else
            {
                while (number!=0)
                {
                    ++count;
                    number/=10;
                }
            }

            return count;
        }
    }

    public static void numberToWords(int number)
    {
        if (number < 0)
            System.out.println("Invalid Value");
        else
        {
            int newNumber = reverse(number);

            if (number == 0)
            {
                System.out.println("Zero");
            }
            else
            {
                while (newNumber!=0)
                {
                    switch (newNumber%10)
                    {
                        case 0:
                            System.out.print("Zero ");
                            break;

                        case 1:
                            System.out.print("One ");
                            break;

                        case 2:
                            System.out.print("Two ");
                            break;

                        case 3:
                            System.out.print("Three ");
                            break;

                        case 4:
                            System.out.print("Four ");
                            break;

                        case 5:
                            System.out.print("Five ");
                            break;

                        case 6:
                            System.out.print("Six ");
                            break;

                        case 7:
                            System.out.print("Seven ");
                            break;

                        case 8:
                            System.out.print("Eight ");
                            break;

                        case 9:
                            System.out.print("Nine ");
                            break;

                        default:
                            System.out.print("Invalid Value");
                            break;
                    }
                    newNumber/=10;
                }
            }

            if (getDigitCount(number) != getDigitCount(reverse(number)))
            {
                int len = getDigitCount(number) - getDigitCount(reverse(number));
                for (int i = 1; i <= len; i++)
                {
                    System.out.print("Zero ");
                }
            }
        }
    }
}
