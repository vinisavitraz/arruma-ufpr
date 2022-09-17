package com.ufpr.br.arrumaufpr.location;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@Controller
public class LocationController {

    @GetMapping("/locations")
    public String listLocations(Model model) {
        List<LocationModel> locations;
        try {
            locations = LocationRepository.getLocations();
        } catch (SQLException e) {
            throw new RuntimeException(e); //create error page or message
        }

        model.addAttribute("locations", locations.size() > 0 ? locations : null);

        return "location/locations";
    }

    @GetMapping("/create-location")
    public String createLocation(Model model) {
        model.addAttribute("title", "Adicionar Local");
        model.addAttribute("textButton", "Salvar");
        model.addAttribute("action", "/create-location");
        model.addAttribute("location", new LocationModel());

        return "location/form-location";
    }

    @GetMapping("/update-location/{id}")
    public String updateLocation(Model model, @PathVariable("id") long id) {
        LocationModel location;
        try {
            location = LocationRepository.getLocationByIDOrNull(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        model.addAttribute("title", "Editar local");
        model.addAttribute("textButton", "Salvar");
        model.addAttribute("action", "/update-location");
        model.addAttribute("location", location);

        return "location/form-location";
    }

    @GetMapping("/delete-location/{id}")
    public String deleteLocation(@PathVariable("id") long id) {
        LocationModel location;
        try {
            location = LocationRepository.getLocationByIDOrNull(id);
            LocationRepository.deleteLocation(location);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/locations";
    }

    @PostMapping("/create-location")
    public String createLocation(@Valid LocationModel location, BindingResult result) {
        if (result.hasFieldErrors()) {
            return "redirect:/create-location";
        }

        try {
            LocationRepository.createLocation(location);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/locations";
    }

    @PostMapping("/update-location")
    public String updateLocation(@Valid LocationModel location, BindingResult result) {
        if (result.hasFieldErrors()) {
            return "redirect:/update-location/" + location.getId();
        }

        try {
            LocationRepository.updateLocation(location);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/locations";
    }
}
