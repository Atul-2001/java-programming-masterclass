package com.signature;

public class Star extends HeavenlyBody {

    public Star(String name, double orbitalPeriod) {
        super(name, orbitalPeriod, BodyType.STAR);
    }

    @Override
    public boolean addSatellite(HeavenlyBody moon) {
        if (moon.getKey().getBodyType() == BodyType.PLANET) {
            return super.addSatellite(moon);
        } else {
            return false;
        }
    }
}
