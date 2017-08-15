package io.vkumar.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vkumar.entities.*;
import io.vkumar.services.CategoryService;
import io.vkumar.services.ProjectTreeService;
import io.vkumar.services.SheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class AjaxController {

    @Autowired
    ProjectTreeService projectTreeService;

    @Autowired
    SheetService sheetService;

    @ResponseBody
    @RequestMapping(value="/api/sheet/save", method= RequestMethod.POST)
    public Boolean getSheetHeader(@RequestParam("rowId") int rowId,
                                  @RequestParam("colId") String colId,
                                  @RequestParam("newVal") String newVal) {



        return sheetService.saveSheet(rowId, colId, newVal);
    }


    @ResponseBody
    @RequestMapping(value="/api/getOptions", method= RequestMethod.POST)
    public List<Option> getOptions(@RequestParam("optionKey") String optionKey) {
        return sheetService.getOptions(optionKey);
    }

    @ResponseBody
    @RequestMapping(value="/api/getJoin", method= RequestMethod.POST)
    public SelectResponse getJoinCol(@RequestParam("sid") int sheetId,
                                   @RequestParam("q") String query,
                                   @RequestParam("col") String colName) {

        return sheetService.getJoinCol(sheetId,colName,query);
    }


    @ResponseBody
    @RequestMapping(value="/api/getHeaders", method= RequestMethod.POST)
    public List<MetaData> getSheetHeader() {
        return sheetService.getSheetHeader();
    }






    @RequestMapping(value="/api/viewDatatable", method= RequestMethod.POST)
    public DatatableResponse getBucket(DatatableRequest datatableRequest) {

        long startTime = System.nanoTime();

        DatatableResponse datatableResponse =  sheetService.getView(datatableRequest);

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        double seconds = (double)elapsedTime / 1000000000.0;
        System.out.println("Took "+seconds + " secs");
        return datatableResponse;

    }


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
