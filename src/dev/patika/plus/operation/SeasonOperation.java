package dev.patika.plus.operation;

import dev.patika.plus.util.Util;

import java.util.HashMap;

public class SeasonOperation {
    public static HashMap<Integer, Integer> parse(String accommodationPrices) {
        return Util.map(accommodationPrices);
    }
}
