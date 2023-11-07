package dev.patika.plus.operation;

import dev.patika.plus.entity.Property;
import dev.patika.plus.essential.Database;
import dev.patika.plus.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class PropertyOperation {
    public static Property retrieve(int id) {
        String query = "SELECT * FROM property WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Property property = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                property = new Property(resultSet);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            Util.close(preparedStatement, resultSet);
        }
        return property;
    }

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

    public static ArrayList<String> retrieveNames(String ids) {
        ArrayList<Integer> idList = new ArrayList<>();
        for (String id : ids.split(",")) {
            idList.add(Integer.parseInt(id));
        }
        return retrieveNames(idList);
    }

    public static String[] retrieveAllNames(String ofType) {
        HashMap<Integer, String> properties = retrieveAll(ofType);
        String[] names = new String[properties.size()];
        int i = 0;
        for (String name : properties.values()) {
            names[i++] = name;
        }
        return names;
    }

    public static HashMap<Integer, String> retrieveAll(String ofType) {
        HashMap<Integer, String> properties = new HashMap<>();

        String query = "SELECT * FROM property WHERE of_type = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Property property = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setString(1, ofType);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                property = new Property(resultSet);
                properties.put(property.getId(), property.getName());
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            Util.close(preparedStatement, resultSet);
        }

        return properties;
    }
}
