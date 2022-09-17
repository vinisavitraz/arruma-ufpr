package com.ufpr.br.arrumaufpr.object;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String createObject() {
        return "object/create-object";
    }

}
