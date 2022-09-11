package com.signature;

public class Vehicle {

    private String name;
    private String size;
    private String fuelType;

    private int currentVelocity;
    private int currentDirection;

    public Vehicle(String name, String size, String fuelType) {
        this.name = name;
        this.size = size;
        this.fuelType = fuelType;
        this.currentVelocity = 0;
        this.currentDirection = 0;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public String getFuelType() {
        return fuelType;
    }

    public int getCurrentVelocity() {
        return currentVelocity;
    }

    public int getCurrentDirection() {
        return currentDirection;
    }

    public void steer(int direction) {
        this.currentDirection += direction;
        System.out.println("Vehicle.steer(): Steering at "+currentDirection+" degrees.");
    }

    public void move(int velocity, int direction) {
        currentVelocity = velocity;
        currentDirection = direction;
        System.out.println("Vehicle.move(): Moving at "+currentVelocity+" in direction "+currentDirection);
    }

    public void stop() {
        this.currentVelocity = 0;
    }
}
