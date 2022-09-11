package com.signature;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Location implements Serializable {

    private final long serialVersionUID = 1L;

    private final int locationId;
    private final String description;
    public final Map<String, Integer> exists;

    public Location(int locationId, String description) {
        this.locationId = locationId;
        this.description = description;
        this.exists = new LinkedHashMap<>();
        this.exists.put("Q", 0);
    }

    public Location(int locationId, String description, LinkedHashMap<String, Integer> exists) {
        this.locationId = locationId;
        this.description = description;
        this.exists = exists;
        this.exists.put("Q", 0);
    }

    public void addExit(String direction, int location) {
        exists.put(direction, location);
    }

    public int getLocationId() {
        return locationId;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExists() {
        return new LinkedHashMap<>(exists);
    }
}
