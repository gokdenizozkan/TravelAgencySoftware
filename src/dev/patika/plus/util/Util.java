package dev.patika.plus.util;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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
        if (str.isEmpty()) return new HashMap<>();
        String[] parts = str.split(";");
        HashMap<Integer, Integer> mapped = new HashMap<>();
        for (String part : parts) {
            String[] idsAndValues = part.split(",");
            int id = Integer.parseInt(idsAndValues[0]);
            int value = Integer.parseInt(idsAndValues[1]);
            mapped.put(id, value);
        }
        return mapped;
    }

    public static ArrayList<Integer> parseIds(String str) {
        String[] parts = str.split(",");
        ArrayList<Integer> ids = new ArrayList<>();
        for (String part : parts) {
            ids.add(Integer.parseInt(part));
        }
        return ids;
    }

    // Data retrieving

    /**
     * Retrieves the corresponding enum ids from the selected checkboxes.
     * @param components of type Checkboxes.
     * @return ArrayList of corresponding enum ids.
     */
    public static ArrayList<Integer> retrieveSelectedCheckboxIds(Component[] components) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Component component : components) {
            if (!(component instanceof JCheckBox)) {
                continue;
            }

            JCheckBox checkBox = (JCheckBox) component;
            if (checkBox.isSelected()) {
                ids.add((Integer) checkBox.getClientProperty("correspondingEnumId"));
            }
        }
        return ids;
    }

    /**
     * Compares corresponding enum ids and ids retrieved from data source, and sets checkboxes' enabled property
     * accordingly.
     * @param ids of related data (in terms of checkboxes).
     * @param panel on which changes will be made.
     */
    public static void setAsSelectedAccordingTo(ArrayList<Integer> ids, JPanel panel) {
        for (Component component : panel.getComponents()) {
            if (!(component instanceof JCheckBox checkBox)) {
                continue;
            }
            int id = (Integer) checkBox.getClientProperty("correspondingEnumId");
            if (ids.contains(id)) {
                checkBox.setSelected(true);
            }
        }
    }

    /**
     * Maps the given ids and values to a HashMap. May return null if the sizes of the given ArrayLists are not equal.
     * @param ids of type ArrayList<Integer>
     * @param values of type ArrayList<T>
     * @return HashMap<Integer, T>
     * @param <T> type of values
     */
    public static <T> HashMap<Integer, T> mapIdsAndValues(ArrayList<Integer> ids, ArrayList<T> values) {
        if (ids.size() != values.size()) return null;

        HashMap<Integer, T> mapped = new HashMap<>();
        for (int i = 0; i < ids.size(); i++) {
            mapped.put(ids.get(i), values.get(i));
        }
        return mapped;
    }

    // Conversion
    /**
     * Stringifies the given ArrayList of elements.
     * @param list of objects.
     * @return Stringified elements.
     * @param <T> type of elements.
     */
    public static <T> String stringify(ArrayList<T> list) {
        String stringified = "";
        for (T element : list) {
            stringified += element.toString() + ",";
        }
        return stringified;
    }

    public static String stringify(ArrayList<Integer> ids, ArrayList<Integer> values) {
        if (ids.size() != values.size()) {
            System.out.println("Util::stringify: ids and values sizes are not equal.");
            return null;
        }

        String stringified = "";
        for (int i = 0; i < ids.size(); i++) {
            stringified += ids.get(i).toString() + "," + values.get(i).toString() + ";";
        }
        return stringified;
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
