package com.ufpr.br.arrumaufpr.object;

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
public class ObjectController {

    @GetMapping("/objects")
    public String listObjects(Model model) {
        List<ObjectModel> objects;
        try {
            objects = ObjectRepository.getObjects();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        model.addAttribute("objects", objects.size() > 0 ? objects : null);

        return "object/objects";
    }

    @GetMapping("/create-object")
    public String createObject(Model model) {
        model.addAttribute("title", "Adicionar Objeto");
        model.addAttribute("textButton", "Salvar");
        model.addAttribute("action", "/create-object");
        model.addAttribute("object", new ObjectModel());

        return "object/form-object";
    }

    @GetMapping("/detail-object/{id}")
    public String detailObject(Model model, @PathVariable("id") long id) {
        ObjectModel object;
        try {
            object = ObjectRepository.getObjectByIDOrNull(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        model.addAttribute("title", "Editar objeto");
        model.addAttribute("textButton", "Excluir");
        model.addAttribute("action", "/update-object");
        model.addAttribute("readOnly", true);
        model.addAttribute("object", object);

        return "object/form-object";
    }

    @GetMapping("/update-object/{id}")
    public String updateObject(Model model, @PathVariable("id") long id) {
        ObjectModel object;
        try {
            object = ObjectRepository.getObjectByIDOrNull(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        model.addAttribute("title", "Editar objeto");
        model.addAttribute("textButton", "Salvar");
        model.addAttribute("action", "/update-object");
        model.addAttribute("object", object);

        return "object/form-object";
    }

    @GetMapping("/delete-object/{id}")
    public String deleteObject(@PathVariable("id") long id) {
        ObjectModel object;
        try {
            object = ObjectRepository.getObjectByIDOrNull(id);
            ObjectRepository.deleteObject(object);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/objects";
    }

    @PostMapping("/create-object")
    public String createObject(@Valid ObjectModel object, BindingResult result) {
        if (result.hasFieldErrors()) {
            return "redirect:/create-object";
        }

        try {
            ObjectRepository.createObject(object);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/objects";
    }

    @PostMapping("/update-object")
    public String updateObject(@Valid ObjectModel object, BindingResult result) {
        if (result.hasFieldErrors()) {
            return "redirect:/update-object/" + object.getId();
        }

        try {
            ObjectRepository.updateObject(object);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/objects";
    }

}
