package com.signature;

public class Main {

    public static void main(String[] args) {

        Hamburger burger = new Hamburger("Basic", "White", "Sausage", 3.56);
        burger.createHamburger();
        burger.addAddon1("Tomato", 0.27);
        burger.addAddon2("Lettuce", 0.75);
        burger.addAddon3("Cheese", 1.13);
        System.out.println("Total Burger price is $" + burger.createHamburger() + "\n");

        HealthyBurger hBurger = new HealthyBurger("Bacon", 5.67);
        hBurger.addHealthAddon1("Egg", 5.43);
        hBurger.addHealthAddon2("Lettuce", 3.41);
        System.out.println("Total Healthy Burger price is $" + hBurger.createHamburger() + "\n");

        DeluxeHamburger dBurger = new DeluxeHamburger();
        dBurger.addAddon3("Cheese",0.4);
        System.out.println("Total Deluxe Burger price is $" + dBurger.createHamburger());

    }
}
