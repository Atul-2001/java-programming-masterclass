package com.signature;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Locations locations = new Locations();
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        Map<String, String> vocabulary = new HashMap<>();
        vocabulary.put("QUIT", "Q");
        vocabulary.put("EAST", "E");
        vocabulary.put("WEST", "W");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");

//        int loc = 1;
        Location currentLocation = locations.getLocation(64);
        while (true) {
            System.out.println(currentLocation.getDescription());
            if (currentLocation.getLocationId() == 0) {
                break;
            }

            Map<String, Integer> exits = currentLocation.getExists();
            System.out.print("Available exits are :- ");
            for (String exit : exits.keySet()) {
                System.out.print(exit + ", ");
            }
            System.out.println();

            String direction = scan.nextLine().toUpperCase();
            
            if (direction.length() > 1) {
                String[] words = direction.split(" ");
                for (String word : words) {
                    if (vocabulary.containsKey(word)) {
                        direction = vocabulary.get(word);
                        break;
                    }
                }
            }

            if (exits.containsKey(direction)) {
                currentLocation = locations.getLocation(exits.get(direction));
            } else {
                System.out.println("You cannot go in that direction.");
            }
        }
        locations.close();
    }
}
