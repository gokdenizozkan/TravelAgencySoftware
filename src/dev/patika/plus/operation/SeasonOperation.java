package dev.patika.plus.operation;

import dev.patika.plus.entity.Season;
import dev.patika.plus.essential.Database;
import dev.patika.plus.util.Dialog;
import dev.patika.plus.util.Response;
import dev.patika.plus.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class SeasonOperation {
    public static Response add(int hotelId, String name, String startDate, String endDate) {
        int response = -1;
        String query = "INSERT INTO season (hotel_id, name, start, end) VALUES (?, ?, ?, ?)";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, hotelId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, startDate);
            preparedStatement.setString(4, endDate);
            response = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            Util.close(preparedStatement);
        }
        return Response.form(response, "added season");
    }

    public static Response update(int seasonId, String name, String startDate, String endDate) {
        int response = -1;
        String query = "UPDATE season SET name = ?, start = ?, end = ? WHERE id = ?";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, startDate);
            preparedStatement.setString(3, endDate);
            preparedStatement.setInt(4, seasonId);
            response = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            Util.close(preparedStatement);
        }

        return Response.form(response, "updated season");
    }

    public static Response delete(int seasonId) {
        boolean confirmed = Dialog.getPremades().isActionConfirmed("delete");
        if (!confirmed) return Response.form(-1, "deleting season");

        int response = -1;
        String query = "DELETE FROM season WHERE id = ?";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, seasonId);
            response = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            Util.close(preparedStatement);
        }

        return Response.form(response, "deleted season");
    }

    public static ArrayList<Season> retrieveAll(int hotelId) {
        String query = "SELECT * FROM season WHERE hotel_id = ?";
        ArrayList<Season> seasons = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Season season = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, hotelId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                season = new Season(resultSet);
                seasons.add(season);
            }
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
        return seasons;
    }

    public static Season retrieve(int seasonId) {
        String query = "SELECT * FROM season WHERE id = ? LIMIT 1";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Season season = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, seasonId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) season = new Season(resultSet);
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
        return season;
    }

    public static Season retrieve(int hotelId, String date) {
        String query = "SELECT * FROM season WHERE hotel_id = ? AND start <= ? AND end >= ? LIMIT 1";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Season season = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, hotelId);
            preparedStatement.setString(2, date);
            preparedStatement.setString(3, date);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) season = new Season(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(preparedStatement, resultSet);
        }
        return season;
    }

    /**
     * For all dates between the given startDate and endDate, checks if there is a season corresponding to them.
     * If exists, adds to the list.
     * If not, adds null to the list.
     * @param hotelId   hotel id
     * @param startDate inclusive
     * @param endDate   inclusive
     * @return ArrayList of Season
     */
    public static HashSet<Season> retrieveByPeriod(int hotelId, String startDate, String endDate) {
        // get all seasons of a hotel
        String query = "SELECT * FROM season WHERE hotel_id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Season> seasons = new ArrayList<>();
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, hotelId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Season season = new Season(resultSet);
                seasons.add(season);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            Util.close(preparedStatement);
        }

        // compare each season with the given period
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        ArrayList<LocalDate> dates = start.datesUntil(end).collect(Collectors.toCollection(ArrayList::new));

        HashSet<Season> foundSeasons = new HashSet<>();
        for (LocalDate date : dates) {
            int foundCounter = 0;

            for (Season season : seasons) {
                if (season == null) continue;
                LocalDate seasonStart = LocalDate.parse(season.getStart());
                LocalDate seasonEnd = LocalDate.parse(season.getEnd());
                if (date.compareTo(seasonStart) >= 0 && date.compareTo(seasonEnd) <= 0) {
                    foundSeasons.add(season);
                    foundCounter++;
                }
            }
            if (foundCounter == 0) return null;
        }

        return foundSeasons;
    }

    public static void deneme(String date) {
        String query = "SELECT * FROM season WHERE start <= ? AND end >= ?";
        ArrayList<Season> seasons = new ArrayList<>();
        ArrayList<Integer> seasonsIds = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        AtomicReference<ResultSet> resultSet = null;
    }
}
