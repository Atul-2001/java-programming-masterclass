package com.signature;

class Car {
    private String name;
    private boolean engine;
    private int cylinder;
    private int wheel;

    public Car(String name, int cylinder) {
        this.name = name;
        this.engine = true;
        this.cylinder = cylinder;
        this.wheel = 4;
    }

    public String getName() {
        return name;
    }

    public int getCylinder() {
        return cylinder;
    }

    public String startEngine() {
        return "Car -> Start Engine";
    }

    public String accelerate() {
        return "Car -> Accelerate";
    }

    public String brake() {
        return "Car -> Break";
    }
}

class Bugatti extends Car{

    public Bugatti(String name, int cylinder) {
        super(name, cylinder);
    }

    @Override
    public String startEngine() {
        return getClass().getSimpleName()+" -> Start Engine";
    }

    @Override
    public String accelerate() {
        return getClass().getSimpleName()+" -> Accelerate";
    }

    @Override
    public String brake() {
        return getClass().getSimpleName()+" -> Brake";
    }
}

class Lamborghini extends Car{

    public Lamborghini(String name, int cylinder) {
        super(name, cylinder);
    }

    @Override
    public String startEngine() {
        return getName()+" -> Start Engine";
    }

    @Override
    public String accelerate() {
        return getName()+" -> Accelerate";
    }

    @Override
    public String brake() {
        return getName()+" -> Brake";
    }
}

class MercedesBenz extends Car{

    public MercedesBenz(String name, int cylinder) {
        super(name, cylinder);
    }

    @Override
    public String startEngine() {
        return "Mercedes Benz S600 -> Start Engine";
    }

    @Override
    public String accelerate() {
        return "Mercedes Benz S600 -> Accelerate";
    }

    @Override
    public String brake() {
        return "Mercedes Benz S600 -> Brake";
    }
}

public class Main {

    public static void main(String[] args) {
	    Car car = new Car("Base Car", 8);
        System.out.println(car.startEngine());
        System.out.println(car.accelerate());
        System.out.println(car.brake());

        Bugatti chiron = new Bugatti("Bugatti Chiron", 6);
        System.out.println(chiron.startEngine());
        System.out.println(chiron.accelerate());
        System.out.println(chiron.brake());

        Lamborghini aventador = new Lamborghini("Lamborghini Aventador", 6);
        System.out.println(aventador.startEngine());
        System.out.println(aventador.accelerate());
        System.out.println(aventador.brake());

        MercedesBenz s600 = new MercedesBenz("Mercedes Benz S600", 4);
        System.out.println(s600.startEngine());
        System.out.println(s600.accelerate());
        System.out.println(s600.brake());
    }
}
