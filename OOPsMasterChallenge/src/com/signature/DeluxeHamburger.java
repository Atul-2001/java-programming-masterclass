package com.signature;

public class DeluxeHamburger extends Hamburger{

    public DeluxeHamburger() {
        super("Delux", "White", "Sausage & Bacon", 14.54);
        super.addAddon1("Chips", 2.75);
        super.addAddon2("Drink", 1.81);
    }

    @Override
    public void addAddon1(String name, double price) {
        System.out.println("Can't add more in this!");
    }

    @Override
    public void addAddon2(String name, double price) {
        System.out.println("Can't add more in this!");
    }

    @Override
    public void addAddon3(String name, double price) {
        System.out.println("Can't add more in this!");
    }

    @Override
    public void addAddon4(String name, double price) {
        System.out.println("Can't add more in this!");
    }
}
