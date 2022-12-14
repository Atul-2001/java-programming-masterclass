public class driver {
    public static void main(String[] args) {

        System.out.println("Speed Converter : ");
        SpeedConverter.printConversion(10.25);
        SpeedConverter.printConversion(-5.6);
        SpeedConverter.printConversion(95.75);

        System.out.println("\nMegaByte Converter : ");
        MegaBytesConverter.printMegaBytesAndKiloBytes(2500);
        MegaBytesConverter.printMegaBytesAndKiloBytes(-1024);
        MegaBytesConverter.printMegaBytesAndKiloBytes(5000);

        System.out.println("\nBarking Dog : ");
        System.out.println(BarkingDog.shouldWakeUp(true, 1));
        System.out.println(BarkingDog.shouldWakeUp(false, 2));
        System.out.println(BarkingDog.shouldWakeUp(true, 8));
        System.out.println(BarkingDog.shouldWakeUp(true, -1));

        System.out.println("\nLeap Year : ");
        System.out.println(LeapYear.isLeapYear(-1600));
        System.out.println(LeapYear.isLeapYear(1600));
        System.out.println(LeapYear.isLeapYear(2017));
        System.out.println(LeapYear.isLeapYear(2000));

        System.out.println("\nDecimal Comparator : ");
        System.out.println(DecimalComaprator.areEqualByThreeDecimalPlaces(-3.1756, -3.175));
        System.out.println(DecimalComaprator.areEqualByThreeDecimalPlaces(3.175, 3.176));

        System.out.println("\nEqual Sum Checker : ");
        System.out.println(EqualSumChecker.hasEqualSum(1, 1, 1));
        System.out.println(EqualSumChecker.hasEqualSum(1, 1, 2));
        System.out.println(EqualSumChecker.hasEqualSum(1, -1, 0));

        System.out.println("\nTeen Number Checker : ");
        System.out.println(TeenNumberChecker.hasTeen(9, 99, 19));
        System.out.println(TeenNumberChecker.hasTeen(23, 15, 42));
        System.out.println(TeenNumberChecker.hasTeen(22, 23, 24));

    }
}
