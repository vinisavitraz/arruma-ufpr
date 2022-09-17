package com.ufpr.br.cmmsufpr.location;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LocationModel {

    private int id;
    @NotEmpty(message = "Informe o nome do local")
    @Size(min = 1, max = 100)
    private String name;
    @NotEmpty(message = "Informe a descrição do local")
    @Size(min = 1, max = 250)
    private String description;

    public LocationModel() {
    }

    public LocationModel(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BuildingModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
