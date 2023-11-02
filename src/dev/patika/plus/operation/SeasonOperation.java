package dev.patika.plus.operation;

import dev.patika.plus.entity.Season;
import dev.patika.plus.essential.Database;
import dev.patika.plus.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SeasonOperation {

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

    public static boolean add(int hotelId, String name, String startDate, String endDate) {
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
        return response != -1;
    }

    public static boolean update(int seasonId, String name, String startDate, String endDate) {
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

        return response != -1;
    }

    public static boolean delete(int seasonId) {
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

        return response != -1;
    }
}
