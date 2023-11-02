package dev.patika.plus.operation;

import dev.patika.plus.entity.Room;
import dev.patika.plus.essential.Database;
import dev.patika.plus.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomOperation {
    public static void add(Room room) {
        if (exists(room.getId())) update(room);
        else insert(room);
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

    private static void insert(Room room) {
        String query = "INSERT INTO room (hotel_id, of_type, beds, stock, size, facilities, season_id, price_adult, price_child) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, room.getHotelId());
            preparedStatement.setString(2, room.getOfType());
            preparedStatement.setInt(3, room.getBeds());
            preparedStatement.setInt(4, room.getStock());
            preparedStatement.setInt(5, room.getSize());
            preparedStatement.setString(6, room.getFacilities());
            preparedStatement.setInt(7, room.getSeasonId());
            preparedStatement.setInt(8, room.getPriceAdult());
            preparedStatement.setInt(9, room.getPriceChild());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(preparedStatement);
        }
    }

    private static void update(Room room) {
        String query = "UPDATE room SET hotel_id = ?, of_type = ?, beds = ?, stock = ?, size = ?, facilities = ?, season_id = ?, price_adult = ?, price_child = ? WHERE id = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, room.getHotelId());
            preparedStatement.setString(2, room.getOfType());
            preparedStatement.setInt(3, room.getBeds());
            preparedStatement.setInt(4, room.getStock());
            preparedStatement.setInt(5, room.getSize());
            preparedStatement.setString(6, room.getFacilities());
            preparedStatement.setInt(7, room.getSeasonId());
            preparedStatement.setInt(8, room.getPriceAdult());
            preparedStatement.setInt(9, room.getPriceChild());
            preparedStatement.setInt(10, room.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(preparedStatement);
        }
    }
}
