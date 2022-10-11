package com.ufpr.br.arrumaufpr.incident;

import com.ufpr.br.arrumaufpr.location.LocationModel;
import com.ufpr.br.arrumaufpr.object.ObjectModel;

import java.util.Date;

public class IncidentModel {

    private int id;
    private String title;
    private String description;
    private String status;
    private Date openDate;
    private Date endDate;
    private IncidentTypeModel incidentType;
    private ObjectModel object;
    private LocationModel location;

    public IncidentModel() {
        this.incidentType = new IncidentTypeModel();
        this.object = new ObjectModel();
        this.location = new LocationModel();
    }

    public IncidentModel(
            int id,
            String title,
            String description,
            String status,
            Date openDate,
            Date endDate,
            IncidentTypeModel incidentType,
            ObjectModel object,
            LocationModel location
    ) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.openDate = openDate;
        this.endDate = endDate;
        this.incidentType = incidentType;
        this.object = object;
        this.location = location;
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

    public IncidentTypeModel getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(IncidentTypeModel incidentType) {
        this.incidentType = incidentType;
    }

    public ObjectModel getObject() {
        return object;
    }

    public void setObject(ObjectModel object) {
        this.object = object;
    }

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }
}
