package com.signature;

public class Main {

    public static void main(String[] args) {
	// write your code here

        System.out.println("Print Day of Week Challenge :- ");
        DayOfWeekChallenge.printDayOfWeek(1);
        DayOfWeekChallenge.printDayOfWeek(2);
        DayOfWeekChallenge.printDayOfWeek(3);
        DayOfWeekChallenge.printDayOfWeek(7);
        DayOfWeekChallenge.printDayOfWeek(6);
        DayOfWeekChallenge.printDayOfWeek(5);
        DayOfWeekChallenge.printDayOfWeek(4);
        DayOfWeekChallenge.printDayOfWeek(10);

        System.out.println("\nNumber in word :- ");
        NumberInWord.printNumberInWord(0);
        NumberInWord.printNumberInWord(9);
        NumberInWord.printNumberInWord(1);
        NumberInWord.printNumberInWord(5);
        NumberInWord.printNumberInWord(2001);
        NumberInWord.printNumberInWord(-1);

        System.out.println("\nNumber of Days in Month :-");
        System.out.println(NumberOfDaysInMonth.getDaysInMonth(2,2001));
        System.out.println(NumberOfDaysInMonth.getDaysInMonth(9,1947));
        System.out.println(NumberOfDaysInMonth.getDaysInMonth(7,1998));
        System.out.println(NumberOfDaysInMonth.getDaysInMonth(6,2003));
        System.out.println(NumberOfDaysInMonth.getDaysInMonth(5,1970));

        System.out.println("\nSum odd range :-");
        System.out.println(SumOddRange.sumOdd(3,30));
        System.out.println(SumOddRange.sumOdd(15,27));
        System.out.println(SumOddRange.sumOdd(1,100));
        System.out.println(SumOddRange.sumOdd(-1,100));
        System.out.println(SumOddRange.sumOdd(100,100));
        System.out.println(SumOddRange.sumOdd(13,13));

        System.out.println("\nSum of Digit Challenge :- ");
        System.out.println(SumDigitChallenge.sumDigit(-1));
        System.out.println(SumDigitChallenge.sumDigit(5));
        System.out.println(SumDigitChallenge.sumDigit(10));
        System.out.println(SumDigitChallenge.sumDigit(15092001));
        System.out.println(SumDigitChallenge.sumDigit(84));
    }
}
