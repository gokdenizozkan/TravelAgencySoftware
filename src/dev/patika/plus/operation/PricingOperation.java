package dev.patika.plus.operation;

import dev.patika.plus.entity.Pricing;
import dev.patika.plus.entity.Season;
import dev.patika.plus.essential.Database;
import dev.patika.plus.util.Reservation;
import dev.patika.plus.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class PricingOperation {
    public static Pricing retrieve(int roomId, int seasonId) {
        String query = "SELECT * FROM pricing WHERE room_id = ? AND season_id = ?";
        Pricing pricing = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, roomId);
            preparedStatement.setInt(2, seasonId);
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

    public static int calculatePrice(Reservation reservation) {
        LocalDate start = LocalDate.parse(reservation.getStartDate());
        LocalDate end = LocalDate.parse(reservation.getEndDate());

        AtomicInteger price = new AtomicInteger();
        start.datesUntil(end).forEach(date -> {
            Season season = SeasonOperation.retrieve(reservation.getHotelId(), date.toString());
            Pricing pricing = PricingOperation.retrieve(reservation.getRoomId(), season.getId());
            int adultPrice = pricing.getPriceAdult() * reservation.getAdultGuestCount();
            int childPrice = pricing.getPriceChild() * reservation.getChildGuestCount();
            price.addAndGet(adultPrice + childPrice);
        });
        return price.get();
    }
}
