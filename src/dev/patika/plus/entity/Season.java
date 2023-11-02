package dev.patika.plus.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Season {
    private int id = -1;
    private int hotelId = -1;
    private String start = "";
    private String end = "";
    private String name = "";

    public Season(ResultSet resultSet) {
        try {
            this.id = resultSet.getInt("id");
            this.hotelId = resultSet.getInt("hotel_id");
            this.start = resultSet.getString("start");
            this.end = resultSet.getString("end");
            this.name = resultSet.getString("name");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
