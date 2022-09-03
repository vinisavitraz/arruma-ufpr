package com.ufpr.br.cmmsufpr.incident;

import java.util.Date;

public class IncidentModel {

    private int id;
    private String title;
    private String description;
    private String status;
    private Date openDate;
    private Date endDate;

    public IncidentModel(int id, String title, String description, String status, Date openDate, Date endDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.openDate = openDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
