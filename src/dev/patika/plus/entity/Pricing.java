package dev.patika.plus.entity;

import java.sql.ResultSet;

public class Pricing {
    private int roomId;
    private int seasonId;
    private int boardTypeId;
    private int priceAdult;
    private int priceChild;

    public Pricing(ResultSet resultSet) {
        try {
            roomId = resultSet.getInt("room_id");
            seasonId = resultSet.getInt("season_id");
            boardTypeId = resultSet.getInt("board_type_id");
            priceAdult = resultSet.getInt("price_adult");
            priceChild = resultSet.getInt("price_child");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public int getPriceAdult() {
        return priceAdult;
    }

    public void setPriceAdult(int priceAdult) {
        this.priceAdult = priceAdult;
    }

    public int getPriceChild() {
        return priceChild;
    }

    public void setPriceChild(int priceChild) {
        this.priceChild = priceChild;
    }
}
