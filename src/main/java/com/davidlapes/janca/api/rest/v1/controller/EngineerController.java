package com.davidlapes.janca.api.rest.v1.controller;

import com.davidlapes.janca.model.Engineer;
import com.davidlapes.janca.service.EngineerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/engineers")
public class EngineerController {

    private final EngineerService service;

    @Autowired
    public EngineerController(EngineerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Engineer> getAll() {
        return service.getAll();
    }
}
