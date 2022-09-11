package com.signature;

import java.io.*;
import java.util.*;

public class Locations implements Map<Integer, Location> {
    private static final Map<Integer, Location> locations = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {

        try (ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))) {
            for (Location location : locations.values()) {
                locFile.writeObject(location);
            }
        }

//        try (DataOutputStream locFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))) {
//            for(Location location : locations.values()) {
//                locFile.writeInt(location.getLocationId());
//                locFile.writeUTF(location.getDescription());
//                System.out.println("Writing : " + location.getLocationId() + " > " + location.getDescription());
//                System.out.println("Writing " + (location.getExists().size()-1) + " exists");
//                locFile.writeInt(location.getExists().size()-1);
//                for (String direction : location.getExists().keySet()) {
//                    if (!direction.equalsIgnoreCase("Q")) {
//                        System.out.println("\t\t" + direction + " : " + location.getExists().get(direction));
//                        locFile.writeUTF(direction);
//                        locFile.writeInt(location.getExists().get(direction));
//                    }
//                }
//            }
//        }

//        try (BufferedWriter locFile = new BufferedWriter(new FileWriter("locations.txt"));
//             BufferedWriter dirFile = new BufferedWriter(new FileWriter("directions.txt"))){
//
//            for (Location location : locations.values()) {
//                locFile.write(location.getLocationId() + ", " + location.getDescription() + "\n");
//                for (String directions : location.getExists().keySet()) {
//                    if (!directions.equalsIgnoreCase("Q")) {
//                        dirFile.write(location.getLocationId() + "," + directions + "," + location.getExists().get(directions) + "\n");
//                    }
//                }
//            }
//        }
//        FileWriter locFile = null;
//
//        try {
//            locFile = new FileWriter("locations.txt");
//
//            for (Location location : locations.values()) {
//                locFile.write(location.getLocationId() + ", " + location.getDescription() + "\n");
//            }
//            locFile.close();
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        } finally {
//            try {
//                if (locFile != null) {
//                    locFile.close();
//                }
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//                e.printStackTrace();
//            }
//        }
    }

    static {

        try (ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))) {
            boolean eof = false;
            while(!eof) {
                try {
                    Location location = (Location) locFile.readObject();
                    System.out.println("Read Location : " + location.getLocationId() + " : " + location.getDescription());
                    System.out.println("Found " + location.getExists().size() + " exists");

                    locations.put(location.getLocationId(), location);
                } catch (EOFException e) {
                    eof = true;
                } catch (ClassNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try (DataInputStream locFile = new DataInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))) {
//            boolean eof = false;
//            while (!eof) {
//                try {
//                    LinkedHashMap<String, Integer> exists = new LinkedHashMap<>();
//                    int locId = locFile.readInt();
//                    String description = locFile.readUTF();
//                    int numOfExist = locFile.readInt();
//                    System.out.println("Reading : " + locId + " " + description);
//                    System.out.println("Found " + numOfExist + " exists");
//
//                    for (int i = 0; i < numOfExist; i++) {
//                        String direction = locFile.readUTF();
//                        int destination = locFile.readInt();
//                        exists.put(direction, destination);
//                        System.out.println("\t\t" + direction + " : " + destination);
//                    }
//                    locations.put(locId, new Location(locId, description, exists));
//                } catch (EOFException e) {
//                    eof = true;
//                }
//            }
//        } catch (IOException io) {
//            System.out.println(io.getMessage());
//        }

//        Scanner scanner = null;
//        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("locations.txt")))) {
//            scanner.useDelimiter(", ");
//            while (scanner.hasNextLine()) {
//                int loc = scanner.nextInt();
//                scanner.skip(scanner.delimiter());
//                String description = scanner.nextLine();
//                locations.put(loc, new Location(loc, description));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try (BufferedReader dirFile = new BufferedReader(new FileReader("directions.txt"))) {
//                String value;
//            while ((value = dirFile.readLine()) != null) {
////                int loc = scanner.nextInt();
////                scanner.skip(scanner.delimiter());
////
////                String direction = scanner.next();
////                scanner.skip(scanner.delimiter());
////
////                int destination = Integer.parseInt(scanner.nextLine());
//
//                String[] data = value.split(",");
//
//                int loc = Integer.parseInt(data[0]);
//                String direction = data[1];
//                int destination = Integer.parseInt(data[2]);
//
//                locations.get(loc).addExit(direction, destination);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key,value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {
        locations.putAll(m);
    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
