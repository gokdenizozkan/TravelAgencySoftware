package dev.patika.plus.property;

import dev.patika.plus.util.Util;

public enum RoomFacility {
    TV, MINIBAR, GAMING_CONSOLE, SAFE, PROJECTION;

    public int getId() {
        return this.ordinal();
    }

    public String getSimpleName() {
        return Util.simplifyConstantName(this.name());
    }
}
