package io.vkumar.repositories;

import io.vkumar.entities.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    Page<Project> findAll(Pageable pageRequest);


    Project findById(Long id);


}
