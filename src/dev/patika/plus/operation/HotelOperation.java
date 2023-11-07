package dev.patika.plus.operation;

import dev.patika.plus.entity.Hotel;
import dev.patika.plus.essential.Database;
import dev.patika.plus.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class HotelOperation {
    public static void add(Hotel hotel) {
        if (exists(hotel.getId())) update(hotel);
        else insert(hotel);
    }

    public static boolean exists(int hotelId) {
        String query = "SELECT * FROM hotel WHERE id = ? LIMIT 1";
        boolean exists;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, hotelId);

            resultSet = preparedStatement.executeQuery();
            exists = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            exists = false;
        } finally {
            Util.close(preparedStatement, resultSet);
        }

        return exists;
    }

    private static void insert(Hotel hotel) {
        String query = "INSERT INTO hotel (name, province, state, address, email, phone_number, stars, facilities, board_types) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setString(1, hotel.getName());
            preparedStatement.setString(2, hotel.getProvince());
            preparedStatement.setString(3, hotel.getState());
            preparedStatement.setString(4, hotel.getAddress());
            preparedStatement.setString(5, hotel.getEmail());
            preparedStatement.setString(6, hotel.getPhoneNumber());
            preparedStatement.setInt(7, hotel.getStars());
            preparedStatement.setString(8, hotel.getFacilities());
            preparedStatement.setString(9, hotel.getBoardTypes());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(preparedStatement);
        }
    }

    private static void update(Hotel hotel) {
        String query = "UPDATE hotel SET name = ?, province = ?, state = ?, address = ?, email = ?, phone_number = ?, stars = ?, facilities = ?, board_types = ? WHERE id = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setString(1, hotel.getName());
            preparedStatement.setString(2, hotel.getProvince());
            preparedStatement.setString(3, hotel.getState());
            preparedStatement.setString(4, hotel.getAddress());
            preparedStatement.setString(5, hotel.getEmail());
            preparedStatement.setString(6, hotel.getPhoneNumber());
            preparedStatement.setInt(7, hotel.getStars());
            preparedStatement.setString(8, hotel.getFacilities());
            preparedStatement.setString(9, hotel.getBoardTypes());
            preparedStatement.setInt(10, hotel.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(preparedStatement);
        }
    }

    public static boolean delete(int hotelId) {
        // TODO: delete hotel
        System.out.println("WIP::delete hotel");
        return false;
    }

    /**
     * Retrieves the constructed hotel object with data stored in the database by accessing them via its hotelId.
     * @param hotelId of which you want to retrieve hotel object holding data of a specific hotel.
     * @return Hotel object. Can be null if there is no hotel with the given hotelId.
     */
    public static Hotel retrieve(int hotelId) {
        String query = "SELECT * FROM hotel WHERE id = ? LIMIT 1";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Hotel hotel = null;
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, hotelId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) hotel = new Hotel(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(preparedStatement, resultSet);
        }
        return hotel;
    }

    public static ArrayList<Hotel> retrieveAll() {
        String query = "SELECT * FROM hotel";
        ArrayList<Hotel> hotels = new ArrayList<>();

        Statement statement = null;
        ResultSet resultSet = null;
        Hotel hotel = null;
        try {
            statement = Database.getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println("In while");
                hotel = new Hotel(resultSet);
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(statement, resultSet);
        }
        return hotels;
    }

    public static ArrayList<String> retrieveProvinces() {
        String query = "SELECT province FROM hotel";

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = Database.getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            ArrayList<String> provinces = new ArrayList<>();
            while (resultSet.next()) {
                String province = resultSet.getString("province");
                if (!provinces.contains(province)) provinces.add(province);
            }
            return provinces;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            Util.close(statement, resultSet);
        }
    }

    public static ArrayList<String> retrieveStates(String ofProvince) {
        String query = "SELECT state FROM hotel WHERE province = ?";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<String> states = new ArrayList<>();
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setString(1, ofProvince);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String state = resultSet.getString("state");
                if (!states.contains(state)) states.add(state);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            Util.close(preparedStatement, resultSet);
        }
        return states;
    }

    public static Hotel[] retrieveBy(String province, String state) {
        String query = "SELECT * FROM hotel WHERE province = ? AND state = ?";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Hotel> hotels = new ArrayList<>();
        try {
            preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setString(1, province);
            preparedStatement.setString(2, state);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Hotel hotel = new Hotel(resultSet);
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(preparedStatement, resultSet);
        }
        return hotels.toArray(new Hotel[0]);
    }

    public static HashMap<Integer, String> retrieveFacilities(int hotelId) {
        Hotel hotel = retrieve(hotelId);

        ArrayList<Integer> ids = hotel.getFacilitiesParsed();
        ArrayList<String> names = PropertyOperation.retrieveNames(ids);

        HashMap<Integer, String> facilities = new HashMap<>();
        for (int i = 0; i < ids.size(); i++) {
            facilities.put(ids.get(i), names.get(i));
        }
        return facilities;
    }

    public static HashMap<String, Integer> retrieveFacilitiesReversed(int hotelId) {
        Hotel hotel = retrieve(hotelId);

        ArrayList<Integer> ids = hotel.getFacilitiesParsed();
        ArrayList<String> names = PropertyOperation.retrieveNames(ids);

        HashMap<String, Integer> facilities = new HashMap<>();
        for (int i = 0; i < ids.size(); i++) {
            facilities.put(names.get(i), ids.get(i));
        }
        return facilities;
    }

    public static HashMap<Integer, String> retrieveBoardTypes(int hotelId) {
        Hotel hotel = retrieve(hotelId);

        ArrayList<Integer> ids = hotel.getBoardTypesParsed();
        ArrayList<String> names = PropertyOperation.retrieveNames(ids);

        HashMap<Integer, String> facilities = new HashMap<>();
        for (int i = 0; i < ids.size(); i++) {
            facilities.put(ids.get(i), names.get(i));
        }
        return facilities;
    }

    public static HashMap<String, Integer> retrieveBoardTypesReversed(int hotelId) {
        Hotel hotel = retrieve(hotelId);

        ArrayList<Integer> ids = hotel.getBoardTypesParsed();
        ArrayList<String> names = PropertyOperation.retrieveNames(ids);

        HashMap<String, Integer> facilities = new HashMap<>();
        for (int i = 0; i < ids.size(); i++) {
            facilities.put(names.get(i), ids.get(i));
        }
        return facilities;
    }


}
