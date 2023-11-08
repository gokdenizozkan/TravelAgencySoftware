package dev.patika.plus.operation;

import dev.patika.plus.entity.RoomAvailability;
import dev.patika.plus.essential.Database;
import dev.patika.plus.entity.Reservation;
import dev.patika.plus.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationOperation {
    public static void add(Reservation reservation) {
        String query = "INSERT INTO reservation (hotel_id, room_id, board_type_id, start_date, end_date, adult_guest_count, child_guest_count, total_price, contact_name, contact_phone_number, contact_email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, reservation.getHotelId());
            preparedStatement.setInt(2, reservation.getRoomId());
            preparedStatement.setInt(3, reservation.getBoardTypeId());
            preparedStatement.setString(4, reservation.getStartDate());
            preparedStatement.setString(5, reservation.getEndDate());
            preparedStatement.setInt(6, reservation.getAdultGuestCount());
            preparedStatement.setInt(7, reservation.getChildGuestCount());
            preparedStatement.setInt(8, reservation.getTotalPrice());
            preparedStatement.setString(9, reservation.getContactName());
            preparedStatement.setString(10, reservation.getContactPhoneNumber());
            preparedStatement.setString(11, reservation.getContactEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            Util.close(preparedStatement);
        }
    }

    public static void delete(int id) {
        // Room availability updates
        Reservation reservation = retrieve(id);
        LocalDate startDate = LocalDate.parse(reservation.getStartDate());
        LocalDate endDate = LocalDate.parse(reservation.getEndDate());
        int roomId = reservation.getRoomId();

        startDate.datesUntil(endDate).forEach(date -> {
            RoomAvailability roomAvailability = RoomAvailabilityOperation.retrieve(roomId, date.toString());
            roomAvailability.setAmount(roomAvailability.getAmount() + 1);
            RoomAvailabilityOperation.update(roomAvailability);
        });

        // Delete reservation from its own table
        String query = "DELETE FROM reservation WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            Util.close(preparedStatement);
        }
    }

    public static Reservation retrieve(int id) {
        String query = "SELECT * FROM reservation WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Reservation reservation = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) reservation = new Reservation(resultSet);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            Util.close(preparedStatement, resultSet);
        }
        return reservation;
    }

    public static ArrayList<Reservation> retrieveAll() {
        String query = "SELECT * FROM reservation";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) reservations.add(new Reservation(resultSet));
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            Util.close(preparedStatement);
        }
        return reservations;
    }
}
