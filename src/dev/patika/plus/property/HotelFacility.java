package dev.patika.plus.property;

import dev.patika.plus.util.Util;

public enum HotelFacility {
    FREE_PARKING, FREE_WIFI, SWIMMING_POOL, FITNESS_CENTER, HOTEL_CONCIERGE, SPA, ROOM_SERVICE;

    public int getId() {
        return this.ordinal();
    }

    public String getSimpleName() {
        return Util.simplifyConstantName(this.name());
    }
}
