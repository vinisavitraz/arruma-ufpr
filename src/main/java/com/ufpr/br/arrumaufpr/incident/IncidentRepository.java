package com.ufpr.br.arrumaufpr.incident;

import com.ufpr.br.arrumaufpr.database.DatabaseConnection;

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
            IncidentModel incident = new IncidentModel(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("status"),
                rs.getDate("open_date"),
                rs.getDate("end_date")
            );

            results.add(incident);
        }

        statement.close();

        return results;
    }

}
