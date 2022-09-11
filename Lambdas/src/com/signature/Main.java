package com.signature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

//        new Thread(() -> System.out.println("Hello from another thread using lambda")).start();
//
//        new Thread(() -> {
//            System.out.println("Hello World");
//            System.out.println("Lambda with multiple line");
//            System.out.println("Awesome!");
//        }).start();

        List<Employee> employees = new ArrayList<>();
        Employee jay = new Employee("Jay", 30);
        Employee atul = new Employee("Atul", 22);
        Employee vivek = new Employee("Vivek", 24);
        Employee rishu = new Employee("rishu", 26);
        Employee harsh = new Employee("Harsh", 21);

        employees.add(jay);
        employees.add(atul);
        employees.add(vivek);
        employees.add(rishu);
        employees.add(harsh);

//        Collections.sort(employees, new Comparator<Employee>() {
//            @Override
//            public int compare(Employee o1, Employee o2) {
//                return o1.getName().compareToIgnoreCase(o2.getName());
//            }
//        });

//        Collections.sort(employees, (Employee employee1, Employee employee2) -> employee1.getName().compareToIgnoreCase(employee2.getName()));

        Collections.sort(employees, (employee1, employee2) -> employee1.getName().compareToIgnoreCase(employee2.getName()));

        employees.forEach(employee -> {
            System.out.println(employee.getName() + " " + employee.getAge());
        });

//        String sillyString = doStringStuff(new upperConcat() {
//            @Override
//            public String upperAndConcat(String s1, String s2) {
//                return s1.toUpperCase() + " " + s2.toUpperCase();
//            }
//        }, atul.getName(), "Suryavanshi");

//        upperConcat uc = (s1, s2) -> s1.toUpperCase() + " " + s2.toUpperCase();
        upperConcat uc = (s1, s2) -> {
            String result = s1.toUpperCase() + " " + s2.toUpperCase();
            return result;
        };
        String sillyString = doStringStuff(uc, atul.getName(), "Suryavanshi");

        System.out.println(sillyString);

        AnotherClass anotherClass = new AnotherClass();
        System.out.println(anotherClass.doSomething());
    }

    public static String doStringStuff(upperConcat uc, String s1, String s2) {
        return uc.upperAndConcat(s1, s2);
    }
}

class Employee {
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

interface upperConcat {
    public String upperAndConcat(String s1, String s2);
}

class AnotherClass {
    public String doSomething() {
        System.out.println("The Another class's name is : " + getClass().getSimpleName());
//        int i = 0;
//        upperConcat uc = (s1, s2) -> {
//            System.out.println(i);
//            System.out.println("The Lambda expression's class is : " + getClass().getSimpleName()); // this return 'AnotherClass' because lambda is treated as a nested code block of a function where it is used which is associated with class.
//            String result = s1.toUpperCase() + " " + s2.toUpperCase();
//            return result;
//        };

        final int i = 0;
        upperConcat uc = new upperConcat() {
            @Override
            public String upperAndConcat(String s1, String s2) {
                System.out.println("The Lambda expression's class is : " + getClass().getSimpleName()); // this return nothing because anonymous class are treated as another class which doesn't have any name.
                String result = s1.toUpperCase() + " " + s2.toUpperCase();
                System.out.println(i); //int i must be used as final because it is replaces with its value while making instance of the anonymous class.
                return result;
            }
        };

        return Main.doStringStuff(uc, "String 1" , "String 2");
    }
}
