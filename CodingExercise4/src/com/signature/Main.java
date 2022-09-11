package com.signature;

public class Main {

    public static void main(String[] args) {

        System.out.println("Simple Calculator :- ");
        SimpleCalculator calculator = new SimpleCalculator();

        calculator.setFirstNumber(5.0);
        calculator.setSecondNumber(4);

        System.out.println("Add            = "+calculator.getAdditionResult());
        System.out.println("Subtraction    = "+calculator.getSubtractionResult());
        System.out.println("Multiplication = "+calculator.getMultiplicationResult());
        System.out.println("Division       = "+calculator.getDivisionResult());

        calculator.setFirstNumber(5.25);
        calculator.setSecondNumber(0);

        System.out.println("Multiplication = "+calculator.getMultiplicationResult());
        System.out.println("Division       = "+calculator.getDivisionResult());

        System.out.println("First Number  = "+calculator.getFirstNumber());
        System.out.println("Second Number = "+calculator.getSecondNumber());

        System.out.println("\nPerson :- ");
        Person human = new Person();
        human.setFirstName("");
        human.setLastName("");
        human.setAge(10);

        System.out.println("Full Name = "+human.getFullName());
        System.out.println("Teen = "+human.isTeen());

        human.setFirstName("John");    // firstName is set to John
        human.setAge(18);
        System.out.println("fullName= " + human.getFullName());
        System.out.println("teen= " + human.isTeen());

        human.setLastName("Smith");    // lastName is set to Smith
        System.out.println("fullName= " + human.getFullName());


        System.out.println("\nWall :- ");
        Wall wall = new Wall(5,4);
        System.out.println("area= " + wall.getArea());

        wall.setHeight(-1.5);
        System.out.println("width= " + wall.getWidth());
        System.out.println("height= " + wall.getHeight());
        System.out.println("area= " + wall.getArea());

        System.out.println("\n Carpeting Cost Calculator :-");
        Carpet carpet = new Carpet(3.5);
        Floor floor = new Floor(2.75, 4.0);
        Calculator calculate = new Calculator(floor, carpet);
        System.out.println("total= " + calculate.getTotalCost());

        carpet = new Carpet(1.5);
        floor = new Floor(5.4, 4.5);
        calculate = new Calculator(floor, carpet);
        System.out.println("total= " + calculate.getTotalCost());

        System.out.println("\nComplex Number :- ");
        ComplexNumber one = new ComplexNumber(1.0, 1.0);
        ComplexNumber number = new ComplexNumber(2.5, -1.5);
        one.add(1,1);
        System.out.println("one.real= " + one.getReal());
        System.out.println("one.imaginary= " + one.getImaginary());
        one.subtract(number);
        System.out.println("one.real= " + one.getReal());
        System.out.println("one.imaginary= " + one.getImaginary());
        number.subtract(one);
        System.out.println("number.real= " + number.getReal());
        System.out.println("number.imaginary= " + number.getImaginary());

        System.out.println("\nCylinder & Circle :- ");
        Circle circle = new Circle(3.75);
        System.out.println("circle.radius= " + circle.getRadius());
        System.out.println("circle.area= " + circle.getArea());
        Cylinder cylinder = new Cylinder(5.55, 7.25);
        System.out.println("cylinder.radius= " + cylinder.getRadius());
        System.out.println("cylinder.height= " + cylinder.getHeight());
        System.out.println("cylinder.area= " + cylinder.getArea());
        System.out.println("cylinder.volume= " + cylinder.getVolume());

        System.out.println("\nRectangle & Height :- ");
        Rectangle rectangle = new Rectangle(5, 10);
        System.out.println("rectangle.width= " + rectangle.getWidth());
        System.out.println("rectangle.length= " + rectangle.getLength());
        System.out.println("rectangle.area= " + rectangle.getArea());
        Cuboid cuboid = new Cuboid(5,10,5);
        System.out.println("cuboid.width= " + cuboid.getWidth());
        System.out.println("cuboid.length= " + cuboid.getLength());
        System.out.println("cuboid.area= " + cuboid.getArea());
        System.out.println("cuboid.height= " + cuboid.getHeight());
        System.out.println("cuboid.volume= " + cuboid.getVolume());

        System.out.println("\nDistance Between Two Points :- ");
        Point first = new Point(6, 5);
        Point second = new Point(3, 1);
        System.out.println("distance(0,0)= " + first.distance());
        System.out.println("distance(second)= " + first.distance(second));
        System.out.println("distance(2,2)= " + first.distance(2, 2));
        Point point = new Point();
        System.out.println("distance()= " + point.distance());

    }
}
