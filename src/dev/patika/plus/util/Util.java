package dev.patika.plus.util;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Util {
    // GUI
    public static int[] center(Dimension size) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - size.width) / 2;
        int y = (screenSize.height - size.height) / 2;
        return new int[]{x, y};
    }


    // Misc
    public static String simplifyConstantName(String constantName) {
        String[] tokens = constantName.split("_");
        StringBuilder simpleName = new StringBuilder();
        for (String token : tokens) {
            simpleName.append(token.charAt(0)).append(token.substring(1).toLowerCase()).append(" ");
        }

        return simpleName.toString().strip();
    }
}
