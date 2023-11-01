package dev.patika.plus.quality;

import java.util.ArrayList;

public interface Stringifiable {
    default <T> String stringifyList(ArrayList<T> list, String separator) {
        StringBuilder builder = new StringBuilder();
        for (T arg : list) {
            builder.append(arg).append(separator);
        }
        return builder.toString();
    }
}
