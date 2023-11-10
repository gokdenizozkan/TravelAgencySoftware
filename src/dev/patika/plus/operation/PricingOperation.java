package dev.patika.plus.operation;

import dev.patika.plus.entity.Pricing;
import dev.patika.plus.entity.Season;
import dev.patika.plus.essential.Database;
import dev.patika.plus.entity.Reservation;
import dev.patika.plus.util.Response;
import dev.patika.plus.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class PricingOperation {
    public static Response add(int roomId, int seasonId, int boardTypeId, int priceAdult, int priceChild) {
        String query = "INSERT INTO pricing (room_id, season_id, board_type_id, price_adult, price_child) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;

        int response = -1;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, roomId);
            preparedStatement.setInt(2, seasonId);
            preparedStatement.setInt(3, boardTypeId);
            preparedStatement.setInt(4, priceAdult);
            preparedStatement.setInt(5, priceChild);
            response = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            Util.close(preparedStatement);
        }

        return Response.form(response, "adding pricing");
    }

    public static Pricing retrieve(int roomId, int seasonId, int boardTypeId) {
        String query = "SELECT * FROM pricing WHERE room_id = ? AND season_id = ? AND board_type_id = ?";
        Pricing pricing = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, roomId);
            preparedStatement.setInt(2, seasonId);
            preparedStatement.setInt(3, boardTypeId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) pricing = new Pricing(resultSet);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            Util.close(preparedStatement, resultSet);
        }

        return pricing;
    }

    public static ArrayList<Pricing> retrieveAll(int roomId) {
        String query = "SELECT * FROM pricing WHERE room_id = ?";
        ArrayList<Pricing> pricings = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, roomId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) pricings.add(new Pricing(resultSet));
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            Util.close(preparedStatement, resultSet);
        }
        return pricings;
    }

    public static Integer calculatePrice(Reservation reservation) {
        LocalDate start = LocalDate.parse(reservation.getStartDate());
        LocalDate end = LocalDate.parse(reservation.getEndDate());

        AtomicInteger price = new AtomicInteger();
        AtomicBoolean error = new AtomicBoolean(false);
        start.datesUntil(end).forEach(date -> {
            int hotelId = reservation.getHotelId();
            int roomId = reservation.getRoomId();
            int boardTypeId = reservation.getBoardTypeId();
            int adultGuestCount = reservation.getAdultGuestCount();
            int childGuestCount = reservation.getChildGuestCount();

            Season season = SeasonOperation.retrieve(hotelId, date.toString());
            int seasonId = season.getId();

            Pricing pricing = PricingOperation.retrieve(roomId, seasonId, boardTypeId);
            if (pricing == null) {
                error.set(true);
                return;
            }
            int adultPrice = pricing.getPriceAdult() * adultGuestCount;
            int childPrice = pricing.getPriceChild() * childGuestCount;

            price.set(price.get() + (adultPrice + childPrice));
        });
        if (error.get()) return null;
        else return price.get();
    }

    public static boolean exists(int roomId, int boardTypeId) {
        String query = "SELECT * FROM pricing WHERE room_id = ? AND board_type_id = ? LIMIT 1";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean exists = false;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, roomId);
            preparedStatement.setInt(2, boardTypeId);
            resultSet = preparedStatement.executeQuery();
            exists = resultSet.next();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            Util.close(preparedStatement, resultSet);
        }
        return exists;
    }
}
