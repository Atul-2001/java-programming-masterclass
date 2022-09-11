package com.signature;

public class switchCase {

    public static void main(String[] args) {

        char ch = 'B';

        switch (ch)
        {
            case 'A':
                System.out.println("It's A");
                break;

            case 'B':
                System.out.println("It's B");
                break;

            case 'C': case 'D': case 'E':
            System.out.println("It is either C or D or E");
            break;

            default:
                System.out.println("Not sure!");
        }

        String month = "MARCH";

        switch (month.toLowerCase())
        {
            case "january":
                System.out.println("It's jan");
                break;

            case "february":
                System.out.println("It's feb");
                break;

            case "march":
                System.out.println("It's march");
                break;

            default:
                System.out.println("Not sure");
                break;
        }
    }
}
