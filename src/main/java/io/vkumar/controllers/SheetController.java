package io.vkumar.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SheetController {

    @RequestMapping(value="/sheet", method = { RequestMethod.GET, RequestMethod.POST })
    public String showSheet(Model model,@RequestParam("id") int id) {

        String jsFiles[] = {"dataTables.bootstrap4.min.js", "jquery-ui.min.js", "select2.min.js", "notify.min.js"};
        String cssFiles[] = {"jquery-ui.min.css", "select2.min.css"};

        model.addAttribute("jsFiles", jsFiles);
        model.addAttribute("cssFiles", cssFiles);
        return "sheet";

    }
}
