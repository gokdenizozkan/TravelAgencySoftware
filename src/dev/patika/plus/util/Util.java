package dev.patika.plus.util;


import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Toolkit;

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

    public static boolean isAllComponentsFilled(JComponent... components) {
        for (JComponent component : components) {
            if (component == null) continue;

            if (component instanceof JTextField) {
                if (((JTextField) component).getText().isEmpty()) return false;
            }
            if (component instanceof JComboBox) {
                if (((JComboBox<?>) component).getSelectedItem() == null) return false;
            }
            if (component instanceof JPasswordField) {
                if (new String(((JPasswordField) component).getPassword()).isEmpty()) return false;
            }
            if (component instanceof JSpinner) {
                if (((JSpinner) component).getValue() == null) return false;
            }
        }
        return true;
    }
}
