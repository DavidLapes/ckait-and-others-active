package com.davidlapes.janca.api.rest.v1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( path = "/api/v1/public/ping" )
public class PublicPing {

    @GetMapping
    public String ping() {
        return "Server has been pinged!";
    }
}
