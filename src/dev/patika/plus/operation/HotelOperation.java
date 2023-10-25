package dev.patika.plus.operation;

import dev.patika.plus.entity.Hotel;
import dev.patika.plus.essential.Database;
import dev.patika.plus.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class HotelOperation {
    public static HashMap<Integer, Integer> parseRooms(String rooms) {
        return Util.map(rooms);
    }

    public static boolean delete(int hotelId) {
        // TODO: delete hotel
        System.out.println("WIP::delete hotel");
        return false;
    }

    /**
     * Retrieves the constructed hotel object with data stored in the database by accessing them via its hotelId.
     * @param hotelId of which you want to retrieve hotel object holding data of a specific hotel.
     * @return Hotel object. Can be null if there is no hotel with the given hotelId.
     */
    public static Hotel retrieve(int hotelId) {
        String query = "SELECT * FROM hotel WHERE id = ? LIMIT 1";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Hotel hotel = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, hotelId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) hotel = new Hotel(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return hotel;
    }

    public static void add(Hotel hotel) {
        if (exists(hotel.getId())) update(hotel);
        else insert(hotel);
    }

    public static boolean exists(int hotelId) {
        String query = "SELECT * FROM hotel WHERE id = ? LIMIT 1";
        boolean exists = false;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, hotelId);

            resultSet = preparedStatement.executeQuery();
            exists = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return exists;
    }

    private static void insert(Hotel hotel) {
        String query = "INSERT INTO hotel (name, province, state, address, email, phone_number, stars, facilities, board_types, all_rooms, available_rooms) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setString(1, hotel.getName());
            preparedStatement.setString(2, hotel.getProvince());
            preparedStatement.setString(3, hotel.getState());
            preparedStatement.setString(4, hotel.getAddress());
            preparedStatement.setString(5, hotel.getEmail());
            preparedStatement.setString(6, hotel.getPhoneNumber());
            preparedStatement.setInt(7, hotel.getStars());
            preparedStatement.setString(8, hotel.getFacilities());
            preparedStatement.setString(9, hotel.getBoardTypes());
            preparedStatement.setString(10, hotel.getAllRooms());
            preparedStatement.setString(11, hotel.getAvailableRooms());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void update(Hotel hotel) {
        String query = "UPDATE hotel SET name = ?, province = ?, state = ?, address = ?, email = ?, phone_number = ?, stars = ?, facilities = ?, board_types = ?, all_rooms = ?, available_rooms = ? WHERE id = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setString(1, hotel.getName());
            preparedStatement.setString(2, hotel.getProvince());
            preparedStatement.setString(3, hotel.getState());
            preparedStatement.setString(4, hotel.getAddress());
            preparedStatement.setString(5, hotel.getEmail());
            preparedStatement.setString(6, hotel.getPhoneNumber());
            preparedStatement.setInt(7, hotel.getStars());
            preparedStatement.setString(8, hotel.getFacilities());
            preparedStatement.setString(9, hotel.getBoardTypes());
            preparedStatement.setString(10, hotel.getAllRooms());
            preparedStatement.setString(11, hotel.getAvailableRooms());
            preparedStatement.setInt(12, hotel.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}