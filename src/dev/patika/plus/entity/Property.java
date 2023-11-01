package dev.patika.plus.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Property {
    private int id;
    private String name;
    private String ofType;

    public Property(ResultSet resultSet) {
        try {
            this.id = resultSet.getInt("id");
            this.name = resultSet.getString("name");
            this.ofType = resultSet.getString("of_type");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public Object[] constructRow() {
        return new Object[]{id, name};
    }

    public int getId() {
        return id;
    }
}
