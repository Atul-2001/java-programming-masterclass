package com.signature;

public class Car extends Vehicle {

    private int wheels;
    private int doors;
    private int gears;
    private boolean isManual;
    private int currentGear;

    public Car(String name, String size, String fuelType, int wheels, int doors, int gears, boolean isManual) {
        super(name, size, fuelType);
        this.wheels = wheels;
        this.doors = doors;
        this.gears = gears;
        this.isManual = isManual;
        this.currentGear = 1;
    }

    public int getWheels() {
        return wheels;
    }

    public int getDoors() {
        return doors;
    }

    public int getGears() {
        return gears;
    }

    public boolean isManual() {
        return isManual;
    }

    public int getCurrentGear() {
        return currentGear;
    }

    public void changeGear(int currentGear) {
        this.currentGear = currentGear;
        System.out.println("Car.setCurrentGear(): changed to "+this.currentGear+" gear.");
    }

    public void changeVelocity(int speed, int direction) {
        System.out.println("Car.changeVelocity() : velocity "+speed+" direction "+direction);
        move(speed, direction);
    }

}
