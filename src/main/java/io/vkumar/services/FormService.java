package io.vkumar.services;


import io.vkumar.entities.Field;
import io.vkumar.entities.Form;
import io.vkumar.repositories.FieldRepository;
import io.vkumar.repositories.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormService {

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private FieldRepository fieldRepository;

    public Page<Form> listForms(int page, int pageSize) {
        return formRepository.findAll(new PageRequest(page, pageSize, Sort.Direction.ASC, "id"));
    }

    public List<Form> findFormsByProjectId(Long id) {
        return formRepository.findByProjectId(id);
    }


    public Form formById(Long id) {
        return formRepository.findById(id);
    }


    public List<Field> findFieldsByFormId(Long id) {
        return fieldRepository.findByFormId(id);
    }
}
