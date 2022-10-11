package com.ufpr.br.arrumaufpr.location;

import com.ufpr.br.arrumaufpr.database.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LocationRepository {

    public static List<LocationModel> getLocations() throws SQLException {
        List<LocationModel> results = new ArrayList<>();
        DatabaseConnection connection = DatabaseConnection.getInstance();
        Statement statement = connection.getConnection().createStatement();

        ResultSet rs = statement.executeQuery("SELECT * FROM location ORDER BY id");

        while (rs.next()) {
            LocationModel location = new LocationModel(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description")
            );

            results.add(location);
        }

        statement.close();

        return results;
    }

    public static LocationModel getLocationByIDOrNull(long id) throws SQLException {
        LocationModel location = null;
        DatabaseConnection connection = DatabaseConnection.getInstance();
        PreparedStatement statement = connection.getConnection().prepareStatement(
                "SELECT * FROM location WHERE id = ?"
        );
        statement.setLong(1, id);

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            location = new LocationModel(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description")

            );
        }

        statement.close();

        return location;
    }

    public static void createLocation(LocationModel locationModel) throws SQLException {
        String SQL = "INSERT INTO location (id, name, description) VALUES (nextval('location_id'), ?, ?)";
        DatabaseConnection connection = DatabaseConnection.getInstance();

        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SQL);
        preparedStatement.setString(1, locationModel.getName());
        preparedStatement.setString(2, locationModel.getDescription());

        preparedStatement.executeUpdate();
    }

    public static void updateLocation(LocationModel locationModel) throws SQLException {
        String SQL = "UPDATE location SET name = ?, description = ? WHERE id = ?";
        DatabaseConnection connection = DatabaseConnection.getInstance();

        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SQL);
        preparedStatement.setString(1, locationModel.getName());
        preparedStatement.setString(2, locationModel.getDescription());
        preparedStatement.setInt(3, locationModel.getId());

        preparedStatement.executeUpdate();
    }

    public static void deleteLocation(LocationModel locationModel) throws SQLException {
        String SQL = "DELETE FROM location WHERE id = ?";
        DatabaseConnection connection = DatabaseConnection.getInstance();

        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SQL);
        preparedStatement.setInt(1, locationModel.getId());

        preparedStatement.executeUpdate();
    }

}
