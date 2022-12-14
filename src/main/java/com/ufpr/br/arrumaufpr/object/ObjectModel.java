package com.ufpr.br.arrumaufpr.object;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ObjectModel {

    private int id;
    @NotEmpty(message = "Informe o nome do objeto")
    @Size(min = 1, max = 100)
    private String name;
    @NotEmpty(message = "Informe a descrição do objeto")
    @Size(min = 1, max = 250)
    private String description;
    private String status;

    public ObjectModel() {
    }

    public ObjectModel(int id, String name, String description, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
