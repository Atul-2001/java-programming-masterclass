package com.signature;

public class Main {

    public static void main(String[] args) {
	// write your code here

        System.out.println("Check Palindrome number :- ");
        System.out.println(NumberPalindrome.isPalindrome(131));
        System.out.println(NumberPalindrome.isPalindrome(-1221));
        System.out.println(NumberPalindrome.isPalindrome(11212));
        System.out.println(NumberPalindrome.isPalindrome(125));

        System.out.println("\nFirst and last Digit Sum :-");
        System.out.println(FirstLastDigitSum.sumFirstAndLastDigit(152));
        System.out.println(FirstLastDigitSum.sumFirstAndLastDigit(457));
        System.out.println(FirstLastDigitSum.sumFirstAndLastDigit(0));
        System.out.println(FirstLastDigitSum.sumFirstAndLastDigit(5));
        System.out.println(FirstLastDigitSum.sumFirstAndLastDigit(-10));

        System.out.println("\nEven Digit Sum :-");
        System.out.println(EvenDigitSum.getEvenDigitSum(123456789));
        System.out.println(EvenDigitSum.getEvenDigitSum(252));
        System.out.println(EvenDigitSum.getEvenDigitSum(-14));
        System.out.println(EvenDigitSum.getEvenDigitSum(5));

        System.out.println("\nShared Digit :-");
        System.out.println(SharedDigit.hasSharedDigit(12, 23));
        System.out.println(SharedDigit.hasSharedDigit(9, 99));
        System.out.println(SharedDigit.hasSharedDigit(15, 55));

        System.out.println("\nLast Digit Checker :-");
        System.out.println(LastDigitChecker.hasSameLastDigit(41, 22, 71));
        System.out.println(LastDigitChecker.hasSameLastDigit(22, 23, 42));
        System.out.println(LastDigitChecker.hasSameLastDigit(9, 99, 999));

        System.out.println("\nGreatest Common Divisor :-");
        System.out.println(GreatestCommonDivisor.getGreatestCommonDivisor(25,15));
        System.out.println(GreatestCommonDivisor.getGreatestCommonDivisor(12,30));
        System.out.println(GreatestCommonDivisor.getGreatestCommonDivisor(9,18));
        System.out.println(GreatestCommonDivisor.getGreatestCommonDivisor(81,153));

        System.out.println("\n Factor Printer :-");
        FactorPrinter.printFactors(6);
        System.out.println();
        FactorPrinter.printFactors(32);
        System.out.println();
        FactorPrinter.printFactors(10);
        System.out.println();
        FactorPrinter.printFactors(-1);

        System.out.println("\nPerfect Number :-");
        System.out.println(PerfectNumber.isPerfectNumber(6));
        System.out.println(PerfectNumber.isPerfectNumber(28));
        System.out.println(PerfectNumber.isPerfectNumber(5));
        System.out.println(PerfectNumber.isPerfectNumber(-1));

        System.out.println("\nNumber to word :-");
        NumberToWords.numberToWords(123);
        System.out.println();
        NumberToWords.numberToWords(1010);
        System.out.println();
        NumberToWords.numberToWords(1000);
        System.out.println();
        NumberToWords.numberToWords(-12);
        NumberToWords.numberToWords(0);

        System.out.println("\nFlour Packer :-");
        System.out.println(FlourPacker.canPack(1, 0, 4));
        System.out.println(FlourPacker.canPack(1, 0, 5));
        System.out.println(FlourPacker.canPack(0, 5, 4));
        System.out.println(FlourPacker.canPack(2, 2, 11));
        System.out.println(FlourPacker.canPack(-3, 2, 12));
        System.out.println(FlourPacker.canPack(4, 18, 19));
        System.out.println(FlourPacker.canPack(5, 3, 24));
        System.out.println(FlourPacker.canPack(6, 2, 17));

        System.out.println("\nLargest Prime :-");
        System.out.println(LargestPrime.getLargestPrime(21));
        System.out.println(LargestPrime.getLargestPrime(217));
        System.out.println(LargestPrime.getLargestPrime(0));
        System.out.println(LargestPrime.getLargestPrime(45));
        System.out.println(LargestPrime.getLargestPrime(-1));
        System.out.println(LargestPrime.getLargestPrime(7));
        System.out.println(LargestPrime.getLargestPrime(16));

        System.out.println("\nDiagonal Star :- ");
        DiagonalStar.printSquareStar(5);
        System.out.println();
        DiagonalStar.printSquareStar(8);
        System.out.println();
        DiagonalStar.printSquareStar(12);

//        System.out.println("\nInput Calculator :-");
//        InputCalculator.inputThenPrintSumAndAverage();

        System.out.println("\nPaint Job :-");
        System.out.println("All Parameters :-");
        System.out.println(PaintJob.getBucketCount(-3.4,2.1, 1.5, 2));
        System.out.println(PaintJob.getBucketCount(3.4, 2.1, 1.5, 2));
        System.out.println(PaintJob.getBucketCount(2.75, 3.25, 2.5, 1));
        System.out.println("\n All Parameter except extraBuckets :-");
        System.out.println(PaintJob.getBucketCount(-3.4, 2.1, 1.5));
        System.out.println(PaintJob.getBucketCount(3.4, 2.1, 1.5));
        System.out.println(PaintJob.getBucketCount(2.75, 3.25, 2.5));
        System.out.println("\nParameters are area and areaPerBuckets :-");
        System.out.println(PaintJob.getBucketCount(3.4, 1.5));
        System.out.println(PaintJob.getBucketCount(6.26, 2.2));
        System.out.println(PaintJob.getBucketCount(3.26, 0.75));
    }
}
