package com.signature;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Area Calculator : ");
        System.out.println("Area of circle : " + AreaCalculator.area(5.0));
        System.out.println("Area of circle : " + AreaCalculator.area(-1));
        System.out.println("Area of rectangle : " + AreaCalculator.area(5.0, 4.0));
        System.out.println("Area of rectangle : " + AreaCalculator.area(-1.0, 4.0));

        System.out.println("\nMinutes to years dyas calculator : ");
        MinutesToYearsDaysCalculator.printYearsAndDays(525600);
        MinutesToYearsDaysCalculator.printYearsAndDays(1051200);
        MinutesToYearsDaysCalculator.printYearsAndDays(561600);

        System.out.println("Int Equality Printer : ");
        IntEqualityPrinter.printEqual(1, 1, 1);
        IntEqualityPrinter.printEqual(1, 1, 2);
        IntEqualityPrinter.printEqual(-1, -1, -1);
        IntEqualityPrinter.printEqual(1, 2, 3);

        System.out.println("\nPlaying Cat : ");
        System.out.println(PlayingCat.isCatPlaying(true, 10));
        System.out.println(PlayingCat.isCatPlaying(false, 36));
        System.out.println(PlayingCat.isCatPlaying(false, 35));
        System.out.println(PlayingCat.isCatPlaying(true, 33));
    }
}
