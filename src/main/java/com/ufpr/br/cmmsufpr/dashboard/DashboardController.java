package com.ufpr.br.cmmsufpr.dashboard;

import com.ufpr.br.cmmsufpr.building.BuildingModel;
import com.ufpr.br.cmmsufpr.building.BuildingRepository;
import com.ufpr.br.cmmsufpr.incident.IncidentModel;
import com.ufpr.br.cmmsufpr.incident.IncidentRepository;
import com.ufpr.br.cmmsufpr.object.ObjectModel;
import com.ufpr.br.cmmsufpr.object.ObjectRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

@Controller
public class DashboardController {

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("name", "Vinicius Savitraz");

        return "home";
    }

    @GetMapping("/create-building")
    public String createBuilding() {
        return "building/create-building";
    }

    @GetMapping("/create-object")
    public String createObject() {
        return "object/create-object";
    }

    @GetMapping("/create-incident")
    public String createIncident() {
        return "incident/create-incident";
    }

    @GetMapping("/incidents")
    public String listIncidents(Model model) {
        List<IncidentModel> incidents;
        try {
            incidents = IncidentRepository.getIncidents();
        } catch (SQLException e) {
            System.out.println("deu ruim");
            throw new RuntimeException(e); //create error page or message
        }
        System.out.println(incidents.size());
        model.addAttribute("incidents", incidents.size() > 0 ? incidents : null);

        return "incident/incidents";
    }

    @GetMapping("/objects")
    public String listObjects(Model model) {
        List<ObjectModel> objects;
        try {
            objects = ObjectRepository.getObjects();
        } catch (SQLException e) {
            throw new RuntimeException(e); //create error page or message
        }

        model.addAttribute("objects", objects.size() > 0 ? objects : null);

        return "object/objects";
    }

    @GetMapping("/buildings")
    public String listBuildings(Model model) {
        List<BuildingModel> buildings;
        try {
            buildings = BuildingRepository.getBuildings();
        } catch (SQLException e) {
            throw new RuntimeException(e); //create error page or message
        }

        model.addAttribute("buildings", buildings);

        return "building/buildings";
    }

}
