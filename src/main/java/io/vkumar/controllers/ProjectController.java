package io.vkumar.controllers;

import io.vkumar.entities.Project;
import io.vkumar.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    @Value("${project.pageSize}")
    private int pageSize;

    @RequestMapping(value="/projects", method= RequestMethod.GET)
    public String allProjects(@RequestParam(defaultValue = "1") int page, Model model) {

        page = page < 1 ? 0 : page - 1;
        Page<Project> projects = projectService.listProjects(page, pageSize);
        model.addAttribute("projects", projects);
        return "projects/list";

    }


    @RequestMapping(value="/project/view", method= RequestMethod.GET)
    public String project(@RequestParam("id") Long id, Model model) {

        Project project = projectService.projectById(id);
        model.addAttribute("project", project);
        return "projects/index";

    }



}
