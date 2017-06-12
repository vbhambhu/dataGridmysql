package io.vkumar.repositories;


import io.vkumar.entities.Field;
import io.vkumar.entities.Form;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FieldRepository extends CrudRepository<Field, Long> {



    List<Field> findByFormId(Long formId);


}
