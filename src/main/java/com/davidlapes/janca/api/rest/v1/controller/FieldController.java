package com.davidlapes.janca.api.rest.v1.controller;

import com.davidlapes.janca.model.Field;
import com.davidlapes.janca.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( path = "/api/v1/fields" )
public class FieldController {

    private final FieldService service;

    @Autowired
    public FieldController( FieldService service ) {
        this.service = service;
    }

    @GetMapping
    public List<Field> getAll() {
        return service.getAll();
    }
}
