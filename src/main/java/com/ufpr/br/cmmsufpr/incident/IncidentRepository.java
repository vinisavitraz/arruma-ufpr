package com.ufpr.br.cmmsufpr.incident;

import com.ufpr.br.cmmsufpr.database.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IncidentRepository {

    public static List<IncidentModel> getIncidents() throws SQLException {
        List<IncidentModel> results = new ArrayList<>();
        DatabaseConnection connection = DatabaseConnection.getInstance();
        Statement statement = connection.getConnection().createStatement();

        ResultSet rs = statement.executeQuery("SELECT * FROM incident");

        while (rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String description = rs.getString("description");

            results.add(new IncidentModel(id, title, description));
        }

        statement.close();

        return results;
    }

}
