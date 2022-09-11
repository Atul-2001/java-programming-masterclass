package com.signature;

public class Main {

    public static void main(String[] args) {
	    Bugatti chiron = new Bugatti(36);
        System.out.println("Name :- "+chiron.getName());
        System.out.println("Type :- "+chiron.getSize());
        System.out.println("Fuel Type :- "+chiron.getFuelType());
        System.out.println("Wheels :- "+chiron.getWheels());
        System.out.println("Doors  :- "+chiron.getDoors());
        System.out.println("Gears  :- "+chiron.getGears());
        System.out.println("Is manual :- "+chiron.isManual());
        System.out.println("Current Gear :- "+chiron.getCurrentGear());

        chiron.steer(45);
        chiron.accelerate(30);
        chiron.accelerate(20);
        chiron.accelerate(-30);

        System.out.println("Current Gear :- "+chiron.getCurrentGear());
    }
}
