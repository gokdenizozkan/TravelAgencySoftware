package dev.patika.plus.operation;

import dev.patika.plus.essential.Database;
import dev.patika.plus.entity.Reservation;
import dev.patika.plus.util.Util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
