package com.ufpr.br.arrumaufpr.incident;

import com.ufpr.br.arrumaufpr.location.LocationModel;
import com.ufpr.br.arrumaufpr.location.LocationRepository;
import com.ufpr.br.arrumaufpr.object.ObjectModel;
import com.ufpr.br.arrumaufpr.object.ObjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.ArrayList;
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

    @GetMapping("/incident-types")
    public String listIncidentTypes(Model model) {
        List<IncidentTypeModel> incidentTypes;
        try {
            incidentTypes = IncidentRepository.getIncidentTypes();
        } catch (SQLException e) {
            throw new RuntimeException(e); //create error page or message
        }

        model.addAttribute("incidentTypes", incidentTypes.size() > 0 ? incidentTypes : null);

        return "incident/incident-types";
    }

    @GetMapping("/create-incident")
    public String createIncident(Model model) {
        List<IncidentTypeModel> incidentTypes;
        List<ObjectModel> objects;
        List<LocationModel> locations;
        try {
            incidentTypes = IncidentRepository.getIncidentTypes();
            objects = ObjectRepository.getObjects();
            locations = LocationRepository.getLocations();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(objects.size());

        model.addAttribute("title", "Adicionar incidente");
        model.addAttribute("textButton", "Salvar");
        model.addAttribute("action", "/create-incident");
        model.addAttribute("incident", new IncidentModel());
        model.addAttribute("incidentTypes", incidentTypes);
        model.addAttribute("objects", objects);
        model.addAttribute("locations", locations);

        return "incident/form-incident";
    }

    @GetMapping("/detail-incident/{id}")
    public String detailIncident(Model model, @PathVariable("id") long id) {
        IncidentModel incident;
        try {
            incident = IncidentRepository.getIncidentByIDOrNull(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        model.addAttribute("title", "Editar incidente");
        model.addAttribute("textButton", "Excluir");
        model.addAttribute("action", "/update-incident");
        model.addAttribute("readOnly", true);
        model.addAttribute("incident", incident);

        return "incident/form-incident";
    }

    @GetMapping("/update-incident/{id}")
    public String updateIncident(Model model, @PathVariable("id") long id) {
        IncidentModel incident;
        try {
            incident = IncidentRepository.getIncidentByIDOrNull(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        model.addAttribute("title", "Editar incidente");
        model.addAttribute("textButton", "Salvar");
        model.addAttribute("action", "/update-incident");
        model.addAttribute("incident", incident);

        return "incident/form-incident";
    }

    @GetMapping("/delete-incident/{id}")
    public String deleteIncident(@PathVariable("id") long id) {
        IncidentModel incident;
        try {
            incident = IncidentRepository.getIncidentByIDOrNull(id);
            IncidentRepository.deleteIncident(incident);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/incidents";
    }

    @PostMapping("/create-incident")
    public String createIncident(@Valid IncidentModel incident, BindingResult result) {
        if (result.hasFieldErrors()) {
            return "redirect:/create-incident";
        }

        try {
            IncidentRepository.createIncident(incident);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/incidents";
    }

    @PostMapping("/update-incident")
    public String updateIncident(@Valid IncidentModel incident, BindingResult result) {
        if (result.hasFieldErrors()) {
            return "redirect:/update-incident/" + incident.getId();
        }

        try {
            IncidentRepository.updateIncident(incident);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/incidents";
    }

}
