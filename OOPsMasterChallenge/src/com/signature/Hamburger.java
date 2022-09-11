package com.signature;

public class Hamburger {

    private String name;
    private String breadRollType;
    private String meat;
    private double price;

    private String addon1Name;
    private double addon1Price;

    private String addon2Name;
    private double addon2Price;

    private String addon3Name;
    private double addon3Price;

    private String addon4Name;
    private double addon4Price;

    public Hamburger(String name, String breadRollType, String meat, double price) {
        this.name = name;
        this.breadRollType = breadRollType;
        this.meat = meat;
        this.price = price;
    }

    public void addAddon1(String name, double price) {
        this.addon1Name = name;
        this.addon1Price = price;
    }

    public void addAddon2(String name, double price) {
        this.addon2Name = name;
        this.addon2Price = price;
    }

    public void addAddon3(String name, double price) {
        this.addon3Name = name;
        this.addon3Price = price;
    }

    public void addAddon4(String name, double price) {
        this.addon4Name = name;
        this.addon4Price = price;
    }

    public double createHamburger() {
        double hamburgerPrice = this.price;

        System.out.println(this.name + " hamburger on a " + this.breadRollType + " roll with " + this.meat + ", price is $" + this.price);

        if (this.addon1Name != null) {
            hamburgerPrice += addon1Price;
            System.out.println("Added " + this.addon1Name + " for an extra $" + this.addon1Price);
        }

        if (this.addon2Name != null) {
            hamburgerPrice += addon2Price;
            System.out.println("Added " + this.addon2Name + " for an extra $" + this.addon2Price);
        }

        if (this.addon3Name != null) {
            hamburgerPrice += addon3Price;
            System.out.println("Added " + this.addon3Name + " for an extra $" + this.addon3Price);
        }

        if (this.addon4Name != null) {
            hamburgerPrice += addon4Price;
            System.out.println("Added " + this.addon4Name + " for an extra $" + this.addon4Price);
        }

        return hamburgerPrice;
    }
}
