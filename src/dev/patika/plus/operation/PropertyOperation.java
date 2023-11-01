package dev.patika.plus.operation;

import dev.patika.plus.essential.Database;
import dev.patika.plus.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PropertyOperation {
    public static ArrayList<String> retrieveNames(ArrayList<Integer> ids) {
        ArrayList<String> properties = new ArrayList<>();

        String query = "SELECT name FROM property WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            for (int id : ids) {
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next())
                    properties.add(resultSet.getString("name"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            Util.close(preparedStatement, resultSet);
        }

        return properties;
    }
}
