package dev.patika.plus.operation;

import dev.patika.plus.entity.User;
import dev.patika.plus.essential.Database;
import dev.patika.plus.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserOperation {
    public static void add(String username, String password, String ofType) {
        User user = retrieve(username);
        if (user == null) insert(username, password, ofType);
        else update(user.getId(), username, password, ofType);
    }

    public static void add(User user) {
        add(user.getUsername(), user.getPassword(), user.getOfType());
    }

    public static void insert(String username, String password, String ofType) {
        String query = "INSERT INTO user (username, password, of_type) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, ofType);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            Util.close(preparedStatement);
        }
    }

    public static void update(int id, String username, String password, String ofType) {
        String query = "UPDATE user SET username = ?, password = ?, of_type = ? WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, ofType);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            Util.close(preparedStatement);
        }
    }

    public static void delete(int id) {
        String query = "DELETE FROM user WHERE id = ?";
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

    public static User retrieve(int id) {
        String query = "SELECT * FROM user WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) user = new User(resultSet);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            Util.close(preparedStatement, resultSet);
        }
        return user;
    }

    public static User retrieve(String username) {
        String query = "SELECT * FROM user WHERE username = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) user = new User(resultSet);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            Util.close(preparedStatement, resultSet);
        }
        return user;
    }

    public static User[] retrieveAll(String ofType) {
        String query = "SELECT * FROM user WHERE of_type = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User[] users = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setString(1, ofType);
            resultSet = preparedStatement.executeQuery();

            ArrayList<User> userList = new ArrayList<>();
            while (resultSet.next()) userList.add(new User(resultSet));
            users = userList.toArray(new User[0]);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            Util.close(preparedStatement, resultSet);
        }
        return users;
    }

    public static User[] retrieveAll() {
        String query = "SELECT * FROM user";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User[] users = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            ArrayList<User> userList = new ArrayList<>();
            while (resultSet.next()) userList.add(new User(resultSet));
            users = userList.toArray(new User[0]);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            Util.close(preparedStatement, resultSet);
        }
        return users;
    }

    public static String[] retrieveTypes() {
        String query = "SHOW COLUMNS FROM `user` LIKE 'of_type'";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String[] types = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String enumValues = resultSet.getString("Type");
                types = enumValues.substring(enumValues.indexOf("(") + 1, enumValues.indexOf(")")).split(",");
                for (int i = 0; i < types.length; i++) types[i] = types[i].replace("'", "").trim();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            Util.close(preparedStatement, resultSet);
        }
        return types;
    }
}
