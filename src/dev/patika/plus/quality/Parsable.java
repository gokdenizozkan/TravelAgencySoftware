package dev.patika.plus.quality;

import java.util.ArrayList;

public interface Parsable {
    /**
     * Parses the given string and returns an ArrayList of integers.
     * @param str of type String that holds integer values separated by the given separator.
     * @param separator of type String that separates the integer values.
     * @return ArrayList of integers.
     */
    default ArrayList<Integer> parseIntegerList(String str, String separator) {
        String[] parts = str.split(separator);
        ArrayList<Integer> ids = new ArrayList<>();
        for (String part : parts) {
            ids.add(Integer.parseInt(part));
        }
        return ids;
    }
}
