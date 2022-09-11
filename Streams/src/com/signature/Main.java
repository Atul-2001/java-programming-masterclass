package com.signature;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
	    List<String> someBingoNumbers = Arrays.asList(
	            "A1", "A2", "A3",
                "B14", "B15", "B16", "B17",
                "C18", "C19",
                "D20",
                "G41", "G42", "G53", "G64", "G50", "g49",
                "I26", "I17", "I29"
        );

	    List<String> gNumbers = new ArrayList<>();

//	    someBingoNumbers.forEach(number -> {
//	        if (number.toUpperCase().startsWith("G")) {
//	            gNumbers.add(number);
////                System.out.println(number);
//            }
//        });
//
//        gNumbers.sort(String::compareToIgnoreCase); // gNumbers.sort((String s1, String s2) -> s1.compareTo(s2));
//        gNumbers.forEach(System.out::println); //gNumbers.forEach((String s) -> System.out.println(s));

        someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .forEach(System.out::println);

        Stream<String> inNumberStream = Stream.of("I26", "I17", "I29", "O71");
        Stream<String> ioNumberStream = Stream.of("N40", "N36","I26", "I17", "I29", "O71");
        Stream<String> concatStream = Stream.concat(inNumberStream, ioNumberStream);
        System.out.println("===============================");
//        System.out.println(concatStream.count());
//        System.out.println("===============================");
//        System.out.println(concatStream.distinct().count());
//        System.out.println("===============================");
        System.out.println(concatStream.distinct().peek(System.out::println).count()); //peak() method is used for debugging purpose it is not a terminal operation like forEach rather it returns the stream as intermediate operation for further chaining.
        System.out.println("===============================");

        Employee john = new Employee("John Doe", 30);
        Employee jane = new Employee("Jane Deer", 25);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);


        Department hr = new Department("Human Resources");
        hr.addEmployee(jane);
        hr.addEmployee(jack);
        hr.addEmployee(snow);
        Department accounting = new Department("Accounting");
        accounting.addEmployee(john);

        List<Department> departments = new ArrayList<>();
        departments.add(hr);
        departments.add(accounting);

        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .forEach(System.out::println);

        System.out.println("---------------");
//        List<String> sortedGNumbers = someBingoNumbers
//                .stream()
//                .map(String::toUpperCase)
//                .filter(s -> s.startsWith("G"))
//                .sorted()
//                .collect(Collectors.toList());

        List<String> sortedGNumbers = someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        for(String s : sortedGNumbers) {
            System.out.println(s);
        }
        
        Map<Integer, List<Employee>> groupedByAge = departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .collect(Collectors.groupingBy(employee -> employee.getAge()));

        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .reduce((e1, e2) -> e1.getAge() < e2.getAge() ? e1 : e2)
                .ifPresent(System.out::println);

        Stream.of("ABC", "AC", "BAA", "CCCC", "XY", "ST")
                .filter(s -> {
                    System.out.println(s);
                    return s.length() == 3;
                })
                .count();
    }
}
