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
    private String facilities; // 0,1,2,3...
    private int seasonId;
    private int priceAdult;
    private int priceChild;

    public Room(ResultSet resultSet) {
        try {
            this.id = resultSet.getInt("id");
            this.hotelId = resultSet.getInt("hotel_id");
            this.ofType = resultSet.getString("of_type");
            this.beds = resultSet.getInt("beds");
            this.stock = resultSet.getInt("stock");
            this.size = resultSet.getInt("size");
            this.facilities = resultSet.getString("facilities");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public Room(int hottelId, String ofType, int beds, int stock, int size, String facilities) {
        this.hotelId = hottelId;
        this.ofType = ofType;
        this.beds = beds;
        this.stock = stock;
        this.size = size;
        this.facilities = facilities;
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

    public String getOfType() {
        return ofType;
    }

    public void setOfType(String ofType) {
        this.ofType = ofType;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getFacilities() {
        return facilities;
    }

    public ArrayList<Integer> getFacilitiesAsList() {
        ArrayList<Integer> parsedIds = new ArrayList<>();
        String[] idArray = facilities.split(",");
        for (String id : idArray) {
            parsedIds.add(Integer.parseInt(id));
        }
        return parsedIds;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
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
