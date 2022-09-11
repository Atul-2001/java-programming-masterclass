package com.signature;

public class Printer {

    private String model;
    private int tonerLevel;
    private int pagesPrinted;
    private boolean isDuplex;

    public Printer(String model, int tonerLevel, boolean isDuplex) {
        this.model = model;
        if (tonerLevel > -1 && tonerLevel <= 100) {
            this.tonerLevel = tonerLevel;
        } else {
            this.tonerLevel = 0;
        }
        this.pagesPrinted = 0;
        this.isDuplex = isDuplex;
    }

    public int getPagesPrinted() {
        return pagesPrinted;
    }

    public int fillToner(int tonerAmount) {
        if (tonerAmount > 0 && tonerAmount <= 100) {
            if (tonerAmount + this.tonerLevel > 100) {
                return -1;
            } else {
                this.tonerLevel+=tonerAmount;
                return this.tonerLevel;
            }
        } else {
            return -1;
        }
    }

    public int printPages(int pages) {
        int pagesToPrint = pages;
        if (this.isDuplex) {
            pagesToPrint = (pages / 2) + (pages % 2);
            System.out.println("Printing in Duplex Mode");
        }
        this.pagesPrinted += pagesToPrint;
        return pagesToPrint;
    }
}
