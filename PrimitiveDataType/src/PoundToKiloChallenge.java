import java.util.Scanner;

public class PoundToKiloChallenge {

    public static void main(String[] args) {

        double pound,kilo;
        Scanner input = new Scanner(System.in);
        System.out.println("\tPound to kilogram Converter");
        System.out.println("Enter value in Pound : ");
        pound = Double.parseDouble(input.next());
        kilo = pound * 0.45359237;
        System.out.println("result = " + kilo);
    }
}

