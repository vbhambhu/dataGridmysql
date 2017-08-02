package io.vkumar.controllers;

import io.vkumar.entities.Category;
import io.vkumar.entities.ProjectTree;
import io.vkumar.services.CategoryService;
import io.vkumar.services.ProjectTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class AjaxController {

    @Autowired
    ProjectTreeService projectTreeService;


    @ResponseBody
    @RequestMapping(value="/api/getProjectTree", method= RequestMethod.POST)
    public List<ProjectTree> getProjectTree() {
        int userId = 1;
        return projectTreeService.getTree(userId);
    }


    @ResponseBody
    @RequestMapping(value="/api/getFolderTree", method= RequestMethod.POST)
    public List<ProjectTree> getFolderTree(@RequestParam("projectId") String pid,
                                           @RequestParam("folderId") String fid
                                           ) {
        int userId = 1;
        int projectId = Integer.parseInt(pid);
        int folderId = Integer.parseInt(fid);
        return projectTreeService.getFolderTree(userId, projectId, folderId);
    }
}
