package io.vkumar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    @RequestMapping(value="/page", method= RequestMethod.GET)
    public String showPage(Model model,@RequestParam("id") int id) {
        return "page";
    }

}
