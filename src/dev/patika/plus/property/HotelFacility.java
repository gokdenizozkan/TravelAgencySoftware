package dev.patika.plus.property;

import dev.patika.plus.util.Util;

public enum HotelFacility {
    FREE_PARKING, FREE_WIFI, SWIMMING_POOL, FITNESS_CENTER, HOTEL_CONCIERGE, SPA, ROOM_SERVICE;

    // To increase performance, we can use an array instead of calling values() method every time.
    public static final HotelFacility[] HOTEL_FACILITIES = HotelFacility.values();

    public static HotelFacility get(int id) {
        return HOTEL_FACILITIES[id];
    }

    public int getId() {
        return this.ordinal();
    }

    public String getSimpleName() {
        return Util.simplifyConstantName(this.name());
    }

}
