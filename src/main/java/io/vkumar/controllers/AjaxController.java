package io.vkumar.controllers;

import io.vkumar.entities.Category;
import io.vkumar.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class AjaxController {

    @Autowired
    CategoryService categoryService;


    @ResponseBody
    @RequestMapping(value="/api/getProjects", method= RequestMethod.POST)
    public List<Category> projectPage() {


        int userId = 1;



        return categoryService.getCategories(userId);


    }
}
