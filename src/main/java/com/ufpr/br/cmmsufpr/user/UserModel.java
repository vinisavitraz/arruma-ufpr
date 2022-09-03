package com.ufpr.br.cmmsufpr.user;

public class UserModel {

    private int id;
    private String name;
    private String email;
    private String type;

    public UserModel(int id, String name, String email, String type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
