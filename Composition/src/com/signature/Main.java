package com.signature;

public class Main {

    public static void main(String[] args) {

        Case theCase = new Case("2208", "Dell","240", new Dimensions(20, 20, 5));
        Monitor monitor = new Monitor("27inch Beast", "Acer", 27, new Resolution(2540,1440));
        Motherboard motherboard = new Motherboard("BJ-200", "Asus", 4, 6, "v2.44");

        PC theRock = new PC(motherboard, theCase, monitor);
        theRock.powerOn();
//        theRock.getTheCase().pressPowerButton();
//        theRock.getMotherboard().loadProgram("jarvis v1.0");
//        theRock.getMonitor().drawPixelAt(1500, 1200, "blue");

    }
}
