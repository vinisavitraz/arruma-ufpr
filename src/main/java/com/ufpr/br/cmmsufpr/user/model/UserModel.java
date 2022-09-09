package com.ufpr.br.cmmsufpr.user.model;

import java.util.List;

public class UserModel {

    private int id;
    private String name;
    private String email;
    private String password;
    private List<PermissionModel> permissions;

    public UserModel(int id, String name, String email, String password, List<PermissionModel> permissions) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.permissions = permissions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PermissionModel> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionModel> permissions) {
        this.permissions = permissions;
    }

}
