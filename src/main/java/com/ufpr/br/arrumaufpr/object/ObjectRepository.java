package com.ufpr.br.arrumaufpr.object;

import com.ufpr.br.arrumaufpr.database.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ObjectRepository {

    public static List<ObjectModel> getObjects() throws SQLException {
        List<ObjectModel> results = new ArrayList<>();
        DatabaseConnection connection = DatabaseConnection.getInstance();
        Statement statement = connection.getConnection().createStatement();

        ResultSet rs = statement.executeQuery("SELECT * FROM object");

        while (rs.next()) {
            ObjectModel object = new ObjectModel(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("status")
            );

            results.add(object);
        }

        statement.close();

        return results;
    }

    public static ObjectModel getObjectByIDOrNull(long id) throws SQLException {
        ObjectModel object = null;
        DatabaseConnection connection = DatabaseConnection.getInstance();
        PreparedStatement statement = connection.getConnection().prepareStatement(
                "SELECT * FROM object WHERE id = ?"
        );
        statement.setLong(1, id);

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            object = new ObjectModel(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("status")

            );
        }

        statement.close();

        return object;
    }

    public static void createObject(ObjectModel objectModel) throws SQLException {
        String SQL = "INSERT INTO object (id, name, description, status) VALUES (nextval('object_id'), ?, ?, ?)";
        DatabaseConnection connection = DatabaseConnection.getInstance();

        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SQL);
        preparedStatement.setString(1, objectModel.getName());
        preparedStatement.setString(2, objectModel.getDescription());
        preparedStatement.setString(2, objectModel.getStatus());

        preparedStatement.executeUpdate();
    }

    public static void updateObject(ObjectModel objectModel) throws SQLException {
        String SQL = "UPDATE object SET name = ?, description = ?, status = ? WHERE id = ?";
        DatabaseConnection connection = DatabaseConnection.getInstance();

        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SQL);
        preparedStatement.setString(1, objectModel.getName());
        preparedStatement.setString(2, objectModel.getDescription());
        preparedStatement.setString(3, objectModel.getStatus());
        preparedStatement.setInt(4, objectModel.getId());

        preparedStatement.executeUpdate();
    }

    public static void deleteObject(ObjectModel objectModel) throws SQLException {
        String SQL = "DELETE FROM object WHERE id = ?";
        DatabaseConnection connection = DatabaseConnection.getInstance();

        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SQL);
        preparedStatement.setInt(1, objectModel.getId());

        preparedStatement.executeUpdate();
    }

}
