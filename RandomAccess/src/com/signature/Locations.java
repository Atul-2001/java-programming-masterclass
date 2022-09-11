package com.signature;

import java.io.*;
import java.util.*;

public class Locations implements Map<Integer, Location> {
    private static final Map<Integer, Location> locations = new LinkedHashMap<>();
    private static Map<Integer, IndexRecord> index = new LinkedHashMap<>();
    private static RandomAccessFile loc;

    public static void main(String[] args) throws IOException {

        try (RandomAccessFile locFile = new RandomAccessFile("locations_rand.dat", "rwd")) {
            locFile.writeInt(locations.size());
            int indexSize = locations.size() * 3 * Integer.BYTES;
            int locationStart = (int) (indexSize + locFile.getFilePointer() + Integer.BYTES);
            locFile.writeInt(locationStart);

            long indexStart = locFile.getFilePointer();

            int startPointer = locationStart;
            locFile.seek(startPointer);

            for (Location location : locations.values()) {
                locFile.writeInt(location.getLocationId());
                locFile.writeUTF(location.getDescription());
                StringBuilder builder = new StringBuilder();
                for (String direction : location.getExists().keySet()) {
                    if (!direction.equalsIgnoreCase("Q")) {
                        builder.append(direction);
                        builder.append(",");
                        builder.append(location.getExists().get(direction));
                        builder.append(",");
                    }
                }
                locFile.writeUTF(builder.toString());

                IndexRecord record = new IndexRecord(startPointer, (int) (locFile.getFilePointer() - startPointer));
                index.put(location.getLocationId(), record);

                startPointer = (int) locFile.getFilePointer();
            }

            locFile.seek(indexStart);
            for (Integer locationID : index.keySet()) {
                locFile.writeInt(locationID);
                locFile.writeInt(index.get(locationID).getStartByte());
                locFile.writeInt(index.get(locationID).getLength());
            }
            System.out.println("File writing completed.");
        }
    }

    static {

        try {
            loc = new RandomAccessFile("locations_rand.dat","rwd");
            int numLocations = loc.readInt();
            int locationStartPointer = loc.readInt();

            while (loc.getFilePointer() < locationStartPointer) {
                int locationID = loc.readInt();
                int locationsStart = loc.readInt();
                int locationLength = loc.readInt();

                IndexRecord record = new IndexRecord(locationsStart, locationLength);
                index.put(locationID, record);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

//        try (ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))) {
//            boolean eof = false;
//            while(!eof) {
//                try {
//                    Location location = (Location) locFile.readObject();
//                    System.out.println("Read Location : " + location.getLocationId() + " : " + location.getDescription());
//                    System.out.println("Found " + location.getExists().size() + " exists");
//
//                    locations.put(location.getLocationId(), location);
//                } catch (EOFException e) {
//                    eof = true;
//                } catch (ClassNotFoundException e) {
//                    System.out.println(e.getMessage());
//                }
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

    public void close() throws IOException {
        loc.close();
    }

    public Location getLocation(int locationID) throws IOException {
        IndexRecord record = index.get(locationID);
        loc.seek(record.getStartByte());

        int locId = loc.readInt();
        String description = loc.readUTF();
        String exists = loc.readUTF();

        String[] rawExits = exists.split(",");

        Location location = new Location(locId, description);

        if (locationID != 0) {
            for (int i = 0; i < rawExits.length; i++) {
                System.out.println("rawExit = " + rawExits[i]);
                System.out.println("rawExit[+i] = " + rawExits[i+1]);
                String direction = rawExits[i];
                int destination = Integer.parseInt(rawExits[++i]);

                location.addExit(direction, destination);
            }
        }

        return location;
    }
}
