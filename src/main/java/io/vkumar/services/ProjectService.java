package io.vkumar.services;

import io.vkumar.entities.Form;
import io.vkumar.entities.Project;
import io.vkumar.repositories.FormRepository;
import io.vkumar.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Page<Project> listProjects(int page, int pageSize) {
        return projectRepository.findAll(new PageRequest(page, pageSize, Sort.Direction.ASC, "id"));
    }


    public Project projectById(Long id) {
        return projectRepository.findById(id);
    }



}
