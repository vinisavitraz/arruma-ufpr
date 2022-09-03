package com.ufpr.br.cmmsufpr.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String home() {
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
    public String listIncidents() {
        return "incident/incidents";
    }

    @GetMapping("/objects")
    public String listObjects() {
        return "object/objects";
    }

    @GetMapping("/buildings")
    public String listBuildings() {
        return "building/buildings";
    }

}
