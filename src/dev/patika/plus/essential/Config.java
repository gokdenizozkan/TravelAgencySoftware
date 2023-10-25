package dev.patika.plus.essential;

import javax.swing.*;

public class Config {
    public static class Database {
        public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
        public static final String URL = "jdbc:mysql://localhost:3306/agency";
        public static final String USERNAME = "root";
        public static final String PASSWORD = "4565";
    }

    public static class Agency {
        public static final String NAME = "Patika Travel Agency";
        public static final String ADDRESS = "İstanbul, Türkiye";
        public static final String PHONE_NUMBER = "+90 555 555 55 55";
        public static final String EMAIL = "";
        public static final String WEBSITE = "patika.dev";
    }

    public static class Program {
        public static final String NAME = "Patika Travel Agency Management System";
        public static final String VERSION = "1.0.0";
        public static final String AUTHOR = "Gökdeniz Özkan";
        public static final String AUTHOR_EMAIL = "gokdenizzkn@outlook.com";
        public static final String AUTHOR_WEBSITE = "gokdenizozkan.com";
        public static final String GITHUB = "github.com/gokdenizozkan";
        public static final String LICENSE = "MIT License";
    }

    public class Gui {
        public static final String TITLE = Program.NAME + " " + Program.VERSION;
        public static final int WIDTH = 1280;
        public static final int HEIGHT = 720;
        public static final int DEFAULT_CLOSE_OPERATION = JFrame.DISPOSE_ON_CLOSE;
    }
}
