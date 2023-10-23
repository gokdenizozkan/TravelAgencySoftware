package dev.patika.plus.util;

import java.awt.*;
import java.util.HashMap;

public class Util {
    // GUI
    public static int[] center(Dimension size) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - size.width) / 2;
        int y = (screenSize.height - size.height) / 2;
        return new int[]{x, y};
    }

    // Database
    public static HashMap<Integer, Integer> map(String str) {
        String[] parts = str.split(";");
        String[] ids = parts[0].split(",");
        String[] values = parts[1].split(",");

        HashMap<Integer, Integer> mapped = new HashMap<>();
        for (int i = 0; i < ids.length; i++) {
            int id = Integer.parseInt(ids[i]);
            int value = Integer.parseInt(values[i]);
            mapped.put(id, value);
        }
        return mapped;
    }

    // Misc
    public static String simplifyConstantName(String constantName) {
        String[] tokens = constantName.split("_");
        StringBuilder simpleName = new StringBuilder();
        for (String token : tokens) {
            simpleName.append(token.charAt(0)).append(token.substring(1).toLowerCase());
        }
        return simpleName.toString();
    }
}
