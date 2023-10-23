package dev.patika.plus.operation;

import dev.patika.plus.util.Util;

import java.util.HashMap;

public class HotelOperation {
    public static HashMap<Integer, Integer> parseRooms(String rooms) {
        return Util.map(rooms);
    }

    public static void delete(int hotelId) {
        // TODO: delete hotel
        System.out.println("WIP::delete hotel");
    }
}
