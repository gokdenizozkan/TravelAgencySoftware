package dev.patika.plus.util;

import dev.patika.plus.essential.Database;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Util {
    // GUI
    public static int[] center(Dimension size) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - size.width) / 2;
        int y = (screenSize.height - size.height) / 2;
        return new int[]{x, y};
    }

    public static void close(java.lang.AutoCloseable... closables) {
        try {
            for (java.lang.AutoCloseable closable : closables) {
                closable.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
