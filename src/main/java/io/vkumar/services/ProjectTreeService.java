package io.vkumar.services;

import io.vkumar.entities.Category;
import io.vkumar.entities.ProjectTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTreeService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<ProjectTree> getTree(int userId){

        // String sqlString = "SELECT cubes.*  FROM cubes LEFT JOIN permissions ON (cubes.id = permissions.project_id) where cubes.type = 'P' AND permissions.project_view = 1 AND permissions.user_id="+userId;
        String sqlString = "SELECT projects.id,projects.name, (select count(*) from folders where folders.project_id=projects.id) as child_count FROM projects";
        List<ProjectTree> projectTrees = jdbcTemplate.query(sqlString, new BeanPropertyRowMapper<ProjectTree>(ProjectTree.class));
        return projectTrees;
    }

    public List<ProjectTree> getFolderTree(int userId, int projectId, int folderId) {

        String sqlString = "SELECT fa.id,fa.name, (select count(*) from folders fb where fb.parent_id=fa.id) as child_count FROM folders fa where project_id = "+projectId+" and fa.parent_id="+folderId;

        System.out.println(sqlString);

        List<ProjectTree> projectTrees = jdbcTemplate.query(sqlString, new BeanPropertyRowMapper<ProjectTree>(ProjectTree.class));
        return projectTrees;


    }
}
