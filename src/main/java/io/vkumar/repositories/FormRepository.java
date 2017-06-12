package io.vkumar.repositories;


import io.vkumar.entities.Form;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FormRepository extends CrudRepository<Form, Long> {

    Page<Form> findAll(Pageable pageRequest);

    Form findById(Long id);

   // Form findByFormId(int formId);

    List<Form> findByProjectId(Long project_Id);


}
