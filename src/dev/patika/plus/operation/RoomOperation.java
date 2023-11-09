package dev.patika.plus.operation;

import dev.patika.plus.entity.Room;
import dev.patika.plus.essential.Database;
import dev.patika.plus.util.Response;
import dev.patika.plus.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomOperation {
    public static Response add(Room room) {
        if (exists(room.getId())) return update(room);
        else return insert(room);
    }

    private static Response insert(Room room) {
        int response = -1;
        String query = "INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, room.getHotelId());
            preparedStatement.setString(2, room.getOfType());
            preparedStatement.setInt(3, room.getBeds());
            preparedStatement.setInt(4, room.getStock());
            preparedStatement.setInt(5, room.getSize());
            preparedStatement.setString(6, room.getFacilities());

            response = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(preparedStatement);
        }
        return Response.form(response, "adding room");
    }

    private static Response update(Room room) {
        String query = "UPDATE room SET hotel_id = ?, of_type = ?, beds = ?, stock = ?, size = ?, facilities = ? WHERE id = ?";
        PreparedStatement preparedStatement = null;

        int response = -1;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, room.getHotelId());
            preparedStatement.setString(2, room.getOfType());
            preparedStatement.setInt(3, room.getBeds());
            preparedStatement.setInt(4, room.getStock());
            preparedStatement.setInt(5, room.getSize());
            preparedStatement.setString(6, room.getFacilities());
            preparedStatement.setInt(7, room.getId());

            response = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(preparedStatement);
        }

        return Response.form(response, "updating room");
    }

    public static boolean exists(int roomId) {
        String query = "SELECT * FROM room WHERE id = ? LIMIT 1";
        boolean exists;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, roomId);

            resultSet = preparedStatement.executeQuery();
            exists = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            exists = false;
        } finally {
            Util.close(preparedStatement, resultSet);
        }

        return exists;
    }

    public static Room retrieve(int roomId) {
        String query = "SELECT * FROM room WHERE id = ? LIMIT 1";
        Room room = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, roomId);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                room = new Room(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(preparedStatement, resultSet);
        }

        return room;
    }

    public static Room retrieveLast() {
        String query = "SELECT * FROM room ORDER BY id DESC LIMIT 1";
        Room room = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                room = new Room(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(preparedStatement, resultSet);
        }

        return room;
    }

    /**
     * Retrieves all rooms found in the given hotel (id).
     * @param hotelId The id of the hotel.
     * @return An ArrayList of Room objects.
     */
    public static ArrayList<Room> retrieveAll(int hotelId) {
        String query = "SELECT * FROM room WHERE hotel_id = ?";
        ArrayList<Room> rooms = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, hotelId);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                rooms.add(new Room(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(preparedStatement, resultSet);
        }

        return rooms;
    }

    /**
     * Retrieves all rooms found in the given hotel (id).
     * @param hotelId The id of the hotel.
     * @return An ArrayList of Room objects.
     */
    public static ArrayList<Room> retrieveAvailables(int hotelId, String startDate, String endDate) {
        String query = "SELECT * FROM room WHERE hotel_id = ?";
        ArrayList<Room> rooms = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean available = false;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, hotelId);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Room room = new Room(resultSet);
                available = RoomAvailabilityOperation.isAvailable(room.getId(), startDate, endDate);
                if (available) rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(preparedStatement, resultSet);
        }

        return rooms;
    }
}
