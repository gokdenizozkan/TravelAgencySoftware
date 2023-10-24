package dev.patika.plus.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Hotel {
    private int id;
    private String name;
    private String province;
    private String state;
    private String address;
    private String email;
    private String phoneNumber;
    private int stars;
    private String facilities;
    private String boardTypes;
    private String allRooms; // roomId, amountOfRoom; represents total rooms, excluding availability
    private String availableRooms; // roomId, amountOfRoom; represents total rooms, excluding availability

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
            this.allRooms = resultSet.getString("all_rooms");
            this.allRooms = resultSet.getString("available_rooms");
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }
}
