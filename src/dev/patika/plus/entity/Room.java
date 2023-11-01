package dev.patika.plus.entity;

import dev.patika.plus.quality.Parsable;
import dev.patika.plus.quality.Stringifiable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Room implements Parsable, Stringifiable {
    private int id;
    private int hotelId;
    private String ofType;
    private int beds;
    private int stock;
    private int size; // in square meter
    private ArrayList<Integer> facilities; // 0,1,2,3...
    private int seasonId;
    private int price;

    public Room(ResultSet resultSet) {
        try {
            this.id = resultSet.getInt("id");
            this.hotelId = resultSet.getInt("hotel_id");
            this.ofType = resultSet.getString("of_type");
            this.beds = resultSet.getInt("beds");
            this.stock = resultSet.getInt("stock");
            this.size = resultSet.getInt("size");
            this.facilities = parseIntegerList(resultSet.getString("facilities"), ",");
            this.seasonId = resultSet.getInt("season_id");
            this.price = resultSet.getInt("price");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
