package com.ufpr.br.cmmsufpr.object;

import com.ufpr.br.cmmsufpr.database.DatabaseConnection;
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
                    rs.getString("status")
            );

            results.add(object);
        }

        statement.close();

        return results;
    }

}
