package dev.patika.plus.property;

public enum RoomType {
    SINGLE, DOUBLE, SUITE;

    public int getId() {
        return this.ordinal();
    }
}
