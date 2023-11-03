package dev.patika.plus.entity;

import java.sql.Date;
import java.sql.ResultSet;

public class RoomAvailability {
    private int roomId;
    private int amount;
    private String date;

    public RoomAvailability(ResultSet resultSet) {
        try {
            roomId = resultSet.getInt("room_id");
            amount = resultSet.getInt("amount");
            date = resultSet.getString("date");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isAvailable() {
        return amount > 0;
    }

    public java.sql.Date getDate() {
        return Date.valueOf(date);
    }

    public String getDateInString() {
        return date;
    }
}
