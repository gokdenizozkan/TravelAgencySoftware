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
    private ArrayList<Integer> facilities = new ArrayList<>(); // 0,1,2,
    private ArrayList<Integer> boardTypes = new ArrayList<>(); // 0,1,2,

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
            this.facilities = parseIntegerList(resultSet.getString("facilities"), ",");
            this.boardTypes = parseIntegerList(resultSet.getString("board_types"), ",");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Constructs a hotel object without availableRooms data.
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
        this.facilities = parseIntegerList((String) data.get(i++), ",");
        this.boardTypes = parseIntegerList((String) data.get(i), ",");
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

    public ArrayList<Integer> getFacilities() {
        return facilities;
    }

    public void setFacilities(ArrayList<Integer> facilities) {
        this.facilities = facilities;
    }

    public ArrayList<Integer> getBoardTypes() {
        return boardTypes;
    }

    public void setBoardTypes(ArrayList<Integer> boardTypes) {
        this.boardTypes = boardTypes;
    }
}
