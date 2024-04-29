package com.davidlapes.janca.api.rest.v1.controller;

import com.davidlapes.janca.service.excel.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/excel")
public class ExcelController {

    private final ExcelService service;

    @Autowired
    public ExcelController(ExcelService service) {
        this.service = service;
    }

    @PostMapping
    public void processExcelEngineers() {
        try {
            service.writeEngineers();
        } catch ( IOException e ) {
            throw new RuntimeException( e );
        }
    }
}
