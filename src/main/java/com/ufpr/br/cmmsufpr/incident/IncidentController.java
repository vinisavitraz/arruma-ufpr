package com.ufpr.br.cmmsufpr.incident;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class IncidentController {

    @GetMapping("/incidents")
    public String listIncidents(Model model) {
        List<IncidentModel> incidents;
        try {
            incidents = IncidentRepository.getIncidents();
        } catch (SQLException e) {
            throw new RuntimeException(e); //create error page or message
        }

        model.addAttribute("incidents", incidents.size() > 0 ? incidents : null);

        return "incident/incidents";
    }

    @GetMapping("/create-incident")
    public String createIncident() {
        return "incident/create-incident";
    }

}
