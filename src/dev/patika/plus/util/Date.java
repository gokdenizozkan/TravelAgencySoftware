package dev.patika.plus.util;

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
