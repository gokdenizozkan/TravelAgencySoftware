package dev.patika.plus.entity;

import dev.patika.plus.quality.Parsable;
import dev.patika.plus.quality.Stringifiable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Hotel implements Stringifiable, Parsable {
    private int id = -1;
    private String name = "";
    private String province = "";
    private String state = "";
    private String address = "";
    private String email = "";
    private String phoneNumber = "";
    private int stars = -1;
    private String facilities = ""; // 1,2,
    private String boardTypes = ""; // 13,14,


    public Hotel(ResultSet resultSet) {
        try {
            this.id = resultSet.getInt("id");
            this.name = resultSet.getString("name");
            this.province = resultSet.getString("province");
            this.state = resultSet.getString("state");
            this.address = resultSet.getString("address");
            this.email = resultSet.getString("email");
            this.phoneNumber = resultSet.getString("phone_number");
            this.stars = resultSet.getInt("stars");
            this.facilities = resultSet.getString("facilities");
            this.boardTypes = resultSet.getString("board_types");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Constructs a hotel object with the given data.
     * @param data of type ArrayList<Object>
     */
    public Hotel(ArrayList<Object> data) {
        int i = 0;
        this.id = (int) data.get(i++);
        this.name = (String) data.get(i++);
        this.province = (String) data.get(i++);
        this.state = (String) data.get(i++);
        this.address = (String) data.get(i++);
        this.email = (String) data.get(i++);
        this.phoneNumber = (String) data.get(i++);
        this.stars = (int) data.get(i++);
        this.facilities = (String) data.get(i++);
        this.boardTypes = (String) data.get(i);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public ArrayList<Integer> getFacilitiesParsed() {
        return parseIntegerList(facilities, ",");
    }

    public void setFacilities(ArrayList<Integer> facilities) {
        this.facilities = stringifyList(facilities, ",");
    }

    public String getBoardTypes() {
        return boardTypes;
    }

    public ArrayList<Integer> getBoardTypesParsed() {
        return parseIntegerList(boardTypes, ",");
    }

    public void setBoardTypes(String boardTypes) {
        this.boardTypes = boardTypes;
    }
}
