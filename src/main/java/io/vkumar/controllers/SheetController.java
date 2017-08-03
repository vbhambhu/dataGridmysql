package io.vkumar.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SheetController {

    @RequestMapping(value="/sheet", method = { RequestMethod.GET, RequestMethod.POST })
    public String showSheet(@RequestParam("id") int id) {

        return "sheet";

    }
}
