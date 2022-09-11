package com.signature;

public class Main {

    public static void main(String[] args) {
        Printer printer = new Printer("HP", 50, true);
        System.out.println("Initial Page Count = "+printer.getPagesPrinted());
        System.out.println("Pages printed was "+printer.printPages(4)+" new total print count for printer = "+printer.getPagesPrinted());
        System.out.println("Pages printed was "+printer.printPages(7)+" new total print count for printer = "+printer.getPagesPrinted());

        Printer print = new Printer("HP", 50, false);
        System.out.println("Initial Page Count = "+print.getPagesPrinted());
        System.out.println("Pages printed was "+print.printPages(3)+" new total print count for printer = "+print.getPagesPrinted());
        System.out.println("Pages printed was "+print.printPages(8)+" new total print count for printer = "+print.getPagesPrinted());
    }
}
