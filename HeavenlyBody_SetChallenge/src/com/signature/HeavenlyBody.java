package com.signature;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class HeavenlyBody {
    private final Key key;
    private final double orbitalPeriod;
    private final Set<HeavenlyBody> satellites;

    public enum BodyType{
        PLANET,
        MOON,
        STAR,
        DWARF_PLANET,
        ASTEROID,
        COMET
    };

    public HeavenlyBody(String name, double orbitalPeriod, BodyType bodyType) {
        this.key = new Key(name, bodyType);
        this.orbitalPeriod = orbitalPeriod;
        this.satellites = new HashSet<>();
    }

    public Key getKey() {
        return key;
    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public Collection<HeavenlyBody> getSatellites() {
        return new HashSet<>(this.satellites);
    }

    public boolean addSatellite(HeavenlyBody moon) {
        return satellites.add(moon);
    }

    public static Key generateKey(String name, BodyType bodyType) {
        return new Key(name, bodyType);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object instanceof HeavenlyBody) {
            HeavenlyBody heavenlyBody = (HeavenlyBody) object;
            return this.key.equals(heavenlyBody.getKey());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.key.hashCode();
    }

    @Override
    public String toString() {
        return this.key.bodyType + ": " + this.key.name + " -> " + this.orbitalPeriod;
    }

    public static final class Key {
        private final String name;
        private final BodyType bodyType;

        public Key(String name, BodyType bodyType) {
            this.name = name;
            this.bodyType = bodyType;
        }

        public String getName() {
            return name;
        }

        public BodyType getBodyType() {
            return bodyType;
        }

        @Override
        public int hashCode() {
            return this.name.hashCode() + 57 + this.bodyType.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            Key key = (Key) obj;
            if (this.name.equals(key.getName())) {
                return (this.bodyType == key.getBodyType());
            } else {
                return false;
            }
        }

        @Override
        public String toString() {
            return this.bodyType + ": " + this.name;
        }
    }
}
