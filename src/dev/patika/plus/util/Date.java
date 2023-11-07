package dev.patika.plus.util;

import javax.swing.JComboBox;


public class Date {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isLeapYear(int year) {
        if (year % 100 == 0) return year % 400 == 0;
        else return year % 4 == 0;
    }

    /**
     * Returns a String array of years between from and to.
     * @param from inclusive
     * @param to inclusive
     * @return String array of years
     */
    public static String[] getYears(int from, int to) {
        String[] years = new String[to - from + 1];
        for (int i = 0; i < years.length; i++) years[i] = String.valueOf(from + i);
        return years;
    }

    public static String[] getMonths() {
        return new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    }

    public static String[] getDays() {
        return new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
                            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
                            "31"};
    }

    public static String[] getDays(String year, String month) {
        return getDays(Integer.parseInt(year), Integer.parseInt(month));
    }

    public static String[] getDays(int year, int month) {
        Month m = Month.values()[month - 1];
        String[] days = new String[m.getDays(year)];
        for (int i = 0; i < days.length; i++) {
            if (i < 10) days[i] = "0" + (i + 1);
            else days[i] = String.valueOf(i + 1);
        }
        return days;
    }

    /**
     * Returns the dateified version of the given date that are found in separate three comboboxes.
     * It simply merges the three comboboxes' selected items.
     * @param yearJcb the combobox for year
     * @param monthJcb the combobox for month
     * @param dayJcb the combobox for day
     * @return dateified version of the given date
     */
    public static String ify(JComboBox yearJcb, JComboBox monthJcb, JComboBox dayJcb) {
        return yearJcb.getSelectedItem().toString() + "-" +
                monthJcb.getSelectedItem().toString() + "-" +
                dayJcb.getSelectedItem().toString();
    }

    public static String[] split(String date) {
        return date.split("-");
    }

    public enum Month {
        JANUARY(1, 31),
        FEBRUARY(2, 28),
        MARCH(3, 31),
        APRIL(4, 30),
        MAY(5, 31),
        JUNE(6, 30),
        JULY(7, 31),
        AUGUST(8, 31),
        SEPTEMBER(9, 30),
        OCTOBER(10, 31),
        NOVEMBER(11, 30),
        DECEMBER(12, 31);

        private final int month;
        private final int days;

        Month(int month, int days) {
            this.month = month;
            this.days = days;
        }

        public int getMonth() {
            return month;
        }

        public int getDays() {
            return days;
        }

        public int getDays(int year) {
            if (this == FEBRUARY && isLeapYear(year)) return 29;
            else return days;
        }
    }
}
