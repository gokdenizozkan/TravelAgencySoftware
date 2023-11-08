package dev.patika.plus.entity;

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

    public RoomAvailability(int roomId, int amount, String date) {
        this.roomId = roomId;
        this.amount = amount;
        this.date = date;
    }

    public boolean isAvailable() {
        return amount > 0;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
