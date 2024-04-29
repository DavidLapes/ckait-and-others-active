package com.davidlapes.janca.service;

import com.davidlapes.janca.model.Field;
import com.davidlapes.janca.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldService {

    private final FieldRepository repository;

    @Autowired
    public FieldService( FieldRepository repository ) {
        this.repository = repository;
    }

    public List<Field> getAll() {
        return repository.findAll();
    }
}
