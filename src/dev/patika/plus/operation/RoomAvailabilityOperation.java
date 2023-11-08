package dev.patika.plus.operation;

import dev.patika.plus.entity.Reservation;
import dev.patika.plus.entity.Room;
import dev.patika.plus.entity.RoomAvailability;
import dev.patika.plus.essential.Database;
import dev.patika.plus.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This class contains methods for room availability operations.
 * For the sake of ease of use and readability, the methods are used through RoomOperation class.
 * If a room has no entry with a specific date on the room_availability table, it is available.
 * If a room has an entry with a specific date on the room_availability table, it is available if the amount is greater than 0.
 * If a room has an entry with a specific date on the room_availability table, it is not available if the amount is 0.
 */
public class RoomAvailabilityOperation {
    protected static boolean isAvailable(int roomId, String date) {
        boolean available = false;
        String query = "SELECT * FROM room_availability WHERE room_id = ? AND date = ?";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, roomId);
            preparedStatement.setString(2, date);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) available = resultSet.getInt("amount") > 0;
            else available = true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            Util.close(preparedStatement, resultSet);
        }
        return available;
    }

    public static boolean isAvailable(int roomId, String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        AtomicBoolean available = new AtomicBoolean(true);
        start.datesUntil(end).forEach(date -> {
            if (!isAvailable(roomId, date.toString())) available.set(false);
        });

        return available.get();
    }

    public static RoomAvailability retrieve(int roomId, String date) {
        String query = "SELECT * FROM room_availability WHERE room_id = ? AND date = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        RoomAvailability roomAvailability = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, roomId);
            preparedStatement.setString(2, date);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) roomAvailability = new RoomAvailability(resultSet);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            Util.close(preparedStatement, resultSet);
        }
        return roomAvailability;
    }

    public static void add(Reservation reservation) {
        LocalDate startDate = LocalDate.parse(reservation.getStartDate());
        LocalDate endDate = LocalDate.parse(reservation.getEndDate());
        int roomId = reservation.getRoomId();

        startDate.datesUntil(endDate).forEach(date -> {
            RoomAvailability roomAvailability = retrieve(roomId, date.toString());
            if (roomAvailability == null) {
                Room room = RoomOperation.retrieve(roomId);
                roomAvailability = new RoomAvailability(roomId, room.getStock() - 1, date.toString());
                insert(roomAvailability);
            }
            else {
                roomAvailability.setAmount(roomAvailability.getAmount() - 1);
                update(roomAvailability);
            }
        });

    }

    public static void insert(RoomAvailability roomAvailability) {
        String query = "INSERT INTO room_availability (room_id, amount, date) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, roomAvailability.getRoomId());
            preparedStatement.setInt(2, roomAvailability.getAmount());
            preparedStatement.setString(3, roomAvailability.getDate());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            Util.close(preparedStatement);
        }
    }

    public static void update(RoomAvailability roomAvailability) {
        String query = "UPDATE room_availability SET amount = ? WHERE room_id = ? AND date = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, roomAvailability.getAmount());
            preparedStatement.setInt(2, roomAvailability.getRoomId());
            preparedStatement.setString(3, roomAvailability.getDate());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            Util.close(preparedStatement);
        }
    }
}
