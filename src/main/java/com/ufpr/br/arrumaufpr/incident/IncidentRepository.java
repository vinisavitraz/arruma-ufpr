package com.ufpr.br.arrumaufpr.incident;

import com.ufpr.br.arrumaufpr.database.DatabaseConnection;
import com.ufpr.br.arrumaufpr.location.LocationModel;
import com.ufpr.br.arrumaufpr.object.ObjectModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IncidentRepository {

    public static List<IncidentTypeModel> getIncidentTypes() throws SQLException {
        List<IncidentTypeModel> results = new ArrayList<>();
        DatabaseConnection connection = DatabaseConnection.getInstance();
        Statement statement = connection.getConnection().createStatement();

        ResultSet rs = statement.executeQuery("SELECT * FROM incident_type ORDER BY id");

        while (rs.next()) {
            IncidentTypeModel incidentType = new IncidentTypeModel(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description")
            );

            results.add(incidentType);
        }

        statement.close();

        return results;
    }

    public static List<IncidentModel> getIncidents() throws SQLException {
        List<IncidentModel> results = new ArrayList<>();
        DatabaseConnection connection = DatabaseConnection.getInstance();
        Statement statement = connection.getConnection().createStatement();

        ResultSet rs = statement.executeQuery("" +
                "SELECT " +
                "   i.id incident_id, i.title incident_title, i.description incident_description, i.status incident_status, i.open_date incident_open_date, i.end_date incident_end_date, " +
                "   it.id incident_type_id, it.name incident_type_name, it.description incident_type_description, " +
                "   o.id object_id, o.name object_name, o.status object_status, o.description object_description, " +
                "   l.id location_id, l.name location_name, l.description location_description " +
                "FROM \"incident\" i " +
                "   inner join \"incident_type\" it on i.incident_type_id = it.id " +
                "   inner join \"object\" o on i.object_id = o.id " +
                "   inner join \"location\" l on i.location_id = l.id ");

        while (rs.next()) {
            IncidentTypeModel incidentType = new IncidentTypeModel(
                    rs.getInt("incident_type_id"),
                    rs.getString("incident_type_name"),
                    rs.getString("incident_type_description")
            );
            ObjectModel object = new ObjectModel(
                    rs.getInt("object_id"),
                    rs.getString("object_name"),
                    rs.getString("incident_type_description"),
                    rs.getString("object_status")
            );
            LocationModel location = new LocationModel(
                    rs.getInt("location_id"),
                    rs.getString("location_name"),
                    rs.getString("location_description")
            );

            IncidentModel incident = new IncidentModel(
                    rs.getInt("incident_id"),
                    rs.getString("incident_title"),
                    rs.getString("incident_description"),
                    rs.getString("incident_status"),
                    rs.getDate("incident_open_date"),
                    rs.getDate("incident_end_date"),
                    incidentType,
                    object,
                    location
            );

            results.add(incident);
        }

        statement.close();

        return results;
    }

    public static IncidentModel getIncidentByIDOrNull(long id) throws SQLException {
        IncidentModel incident = null;
        DatabaseConnection connection = DatabaseConnection.getInstance();

        PreparedStatement statement = connection.getConnection().prepareStatement(
                "SELECT " +
                    "   i.id incident_id, i.title incident_title, i.description incident_description, i.status incident_status, i.open_date incident_open_date, i.end_date incident_end_date, " +
                    "   it.id incident_type_id, it.name incident_type_name, it.description incident_type_description, " +
                    "   o.id object_id, o.name object_name, o.status object_status, o.description object_description, " +
                    "   l.id location_id, l.name location_name, l.description location_description " +
                    "FROM \"incident\" i " +
                    "   inner join \"incident_type\" it on i.incident_type_id = it.id " +
                    "   inner join \"object\" o on i.object_id = o.id " +
                    "   inner join \"location\" l on i.location_id = l.id " +
                    "WHERE id = ?"
        );
        statement.setLong(1, id);

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            IncidentTypeModel incidentType = new IncidentTypeModel(
                    rs.getInt("incident_type_id"),
                    rs.getString("incident_type_name"),
                    rs.getString("incident_type_description")
            );
            ObjectModel object = new ObjectModel(
                    rs.getInt("object_id"),
                    rs.getString("object_name"),
                    rs.getString("incident_type_description"),
                    rs.getString("object_status")
            );
            LocationModel location = new LocationModel(
                    rs.getInt("location_id"),
                    rs.getString("location_name"),
                    rs.getString("location_description")
            );

            incident = new IncidentModel(
                    rs.getInt("incident_id"),
                    rs.getString("incident_title"),
                    rs.getString("incident_description"),
                    rs.getString("incident_status"),
                    rs.getDate("incident_open_date"),
                    rs.getDate("incident_end_date"),
                    incidentType,
                    object,
                    location
            );
        }

        statement.close();

        return incident;
    }

    public static void createIncident(IncidentModel incidentModel) throws SQLException {
        String SQL = "INSERT INTO incident (id, title, description, status, open_date, incident_type_id, object_id, location_id) VALUES (nextval('incident_id'), ?, ?, ?, ?, ?, ?, ?)";
        DatabaseConnection connection = DatabaseConnection.getInstance();

        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SQL);
        preparedStatement.setString(1, incidentModel.getTitle());
        preparedStatement.setString(2, incidentModel.getDescription());
        preparedStatement.setString(3, incidentModel.getStatus());
        preparedStatement.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));
        preparedStatement.setInt(5, incidentModel.getIncidentType().getId());
        preparedStatement.setInt(6, incidentModel.getObject().getId());
        preparedStatement.setInt(7, incidentModel.getLocation().getId());

        preparedStatement.executeUpdate();
    }

    public static void updateIncident(IncidentModel incidentModel) throws SQLException {
        String SQL = "UPDATE incident SET title = ?, description = ?, status = ?, end_date = ?, incident_type_id = ?, object_id = ?, location_id = ? WHERE id = ?";
        DatabaseConnection connection = DatabaseConnection.getInstance();

        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SQL);
        preparedStatement.setString(1, incidentModel.getTitle());
        preparedStatement.setString(2, incidentModel.getDescription());
        preparedStatement.setString(3, incidentModel.getStatus());
        preparedStatement.setDate(4, new java.sql.Date(incidentModel.getEndDate().getTime()));
        preparedStatement.setInt(5, incidentModel.getIncidentType().getId());
        preparedStatement.setInt(6, incidentModel.getObject().getId());
        preparedStatement.setInt(7, incidentModel.getLocation().getId());
        preparedStatement.setInt(8, incidentModel.getId());

        preparedStatement.executeUpdate();
    }

    public static void deleteIncident(IncidentModel incidentModel) throws SQLException {
        String SQL = "DELETE FROM incident WHERE id = ?";
        DatabaseConnection connection = DatabaseConnection.getInstance();

        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SQL);
        preparedStatement.setInt(1, incidentModel.getId());

        preparedStatement.executeUpdate();
    }

}
