package io.vkumar.controllers;


import io.vkumar.entities.Field;
import io.vkumar.entities.Form;
import io.vkumar.services.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;


@Controller
public class FormController {

    @Autowired
    private FormService formService;

    @RequestMapping(value="/form/view", method= RequestMethod.GET)
    public String showform(@RequestParam("id") Long id, Model model) {

        Form form = formService.formById(id);
        model.addAttribute("form", form);
        List<Field> fields = formService.findFieldsByFormId(id);
        model.addAttribute("fields", fields);
        return "forms/index";

    }



}
