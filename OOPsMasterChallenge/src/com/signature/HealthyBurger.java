package com.signature;

public class HealthyBurger extends Hamburger {

    private String addon1Name;
    private double addon1Price;

    private String addon2Name;
    private double addon2Price;

    public HealthyBurger(String meat, double price) {
        super("Healthy", "Brown rye", meat, price);
    }

    public void addHealthAddon1(String name, double price) {
        this.addon1Name = name;
        this.addon1Price = price;
    }

    public void addHealthAddon2(String name, double price) {
        this.addon2Name = name;
        this.addon2Price = price;
    }

    @Override
    public double createHamburger() {
        double healthyHamburgerPrice = super.createHamburger();

        if (this.addon1Name != null) {
            healthyHamburgerPrice += this.addon1Price;
            System.out.println("Added " + this.addon1Name + " for an extra $" + this.addon1Price);
        }
        if (this.addon2Name != null) {
            healthyHamburgerPrice += this.addon2Price;
            System.out.println("Added " + this.addon2Name + " for an extra $" + this.addon2Price);
        }

        return healthyHamburgerPrice;
    }
}
