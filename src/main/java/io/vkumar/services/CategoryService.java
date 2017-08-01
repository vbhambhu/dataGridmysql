package io.vkumar.services;


import io.vkumar.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Category> getCategories(int userId){

       // String sqlString = "SELECT cubes.*  FROM cubes LEFT JOIN permissions ON (cubes.id = permissions.project_id) where cubes.type = 'P' AND permissions.project_view = 1 AND permissions.user_id="+userId;

        String sqlString = "SELECT projects.id,projects.name, (select count(*) from folders where folders.project_id=projects.id) as child_count FROM projects";

        List<Category> categories = jdbcTemplate.query(sqlString, new BeanPropertyRowMapper<Category>(Category.class));

        return categories;
}




}
