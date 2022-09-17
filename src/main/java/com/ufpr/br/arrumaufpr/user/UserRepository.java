package com.ufpr.br.arrumaufpr.user;

import com.ufpr.br.arrumaufpr.database.DatabaseConnection;
import com.ufpr.br.arrumaufpr.user.model.PermissionModel;
import com.ufpr.br.arrumaufpr.user.model.UserModel;
import com.ufpr.br.arrumaufpr.user.model.UserRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public static List<UserModel> getUsers() throws SQLException {
        List<UserModel> results = new ArrayList<>();
        DatabaseConnection connection = DatabaseConnection.getInstance();
        Statement statement = connection.getConnection().createStatement();

        ResultSet rs = statement.executeQuery("SELECT * FROM user");

        while (rs.next()) {
            UserModel user = new UserModel(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    new ArrayList<>()
            );

            results.add(user);
        }

        statement.close();

        return results;
    }

    public static UserModel getUserByIDOrNull(int id) throws SQLException {
        UserModel user = null;
        DatabaseConnection connection = DatabaseConnection.getInstance();
        PreparedStatement statement = connection.getConnection().prepareStatement(
                "SELECT * FROM user WHERE id = ?"
        );
        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            List<PermissionModel> permissions = new ArrayList<>();
            UserRole.Role userRole = UserRole.Role.fromValue(rs.getInt("id"));

            if (userRole != null) {
                permissions = getPermissionsByRole(connection, userRole);
            }

            user = new UserModel(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    permissions
            );
        }

        statement.close();

        return user;
    }

    public static UserModel getUserByEmailOrNull(String email) throws SQLException {
        UserModel user = null;
        DatabaseConnection connection = DatabaseConnection.getInstance();
        PreparedStatement statement = connection.getConnection().prepareStatement(
                "SELECT * FROM user WHERE email = ?"
        );
        statement.setString(1, email);

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            List<PermissionModel> permissions = new ArrayList<>();
            UserRole.Role userRole = UserRole.Role.fromValue(rs.getInt("id"));

            if (userRole != null) {
                permissions = getPermissionsByRole(connection, userRole);
            }

            user = new UserModel(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    permissions

            );
        }

        statement.close();

        return user;
    }

    public static void saveUser(UserModel user) {

    }

    private static List<PermissionModel> getPermissionsByRole(DatabaseConnection connection, UserRole.Role userRole) throws SQLException {
        List<PermissionModel> permissions = new ArrayList<>();
        PreparedStatement statement = connection.getConnection().prepareStatement(
                "SELECT p.id \"id\", p.name as \"name\" FROM role_permissions rp INNER JOIN permission p\n" +
                        "ON rp.permission_id = p.id\n" +
                        "WHERE rp.role_id = ?"

        );
        statement.setInt(1, userRole.getValue());

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            PermissionModel permission = new PermissionModel(
                    rs.getInt("id"),
                    rs.getString("name")
            );
            permissions.add(permission);
        }

        return permissions;
    }

}
