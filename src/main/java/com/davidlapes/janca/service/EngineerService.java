package com.davidlapes.janca.service;

import com.davidlapes.janca.model.Engineer;
import com.davidlapes.janca.repository.EngineerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngineerService {

    private final EngineerRepository repository;

    @Autowired
    public EngineerService(EngineerRepository repository) {
        this.repository = repository;
    }

    public Engineer create(Engineer engineer) {
        return repository.saveAndFlush( engineer );
    }

    public List<Engineer> getAll() {
        return repository.findAll();
    }

    public Engineer getById( String id ) {
        return repository.findById( id ).orElseThrow();
    }
}
