package com.ufpr.br.cmmsufpr.incident;

import com.ufpr.br.cmmsufpr.database.DatabaseConnection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class IncidentController {

    @GetMapping("/incident")
    public List<IncidentModel> getIncidents() {
        List<IncidentModel> incidents;

        try {
            incidents = IncidentRepository.getIncidents();
        } catch (SQLException e) {
            throw new RuntimeException(e); //TODO: return error object
        }

        return incidents;
    }

}
