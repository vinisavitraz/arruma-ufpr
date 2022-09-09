package com.ufpr.br.cmmsufpr.building;

import com.ufpr.br.cmmsufpr.database.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BuildingRepository {

    public static List<BuildingModel> getBuildings() throws SQLException {
        List<BuildingModel> results = new ArrayList<>();
        DatabaseConnection connection = DatabaseConnection.getInstance();
        Statement statement = connection.getConnection().createStatement();

        ResultSet rs = statement.executeQuery("SELECT * FROM building");

        while (rs.next()) {
            BuildingModel building = new BuildingModel(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("address")
            );

            results.add(building);
        }

        statement.close();

        return results;
    }

}
