package com.davidlapes.janca.mapper;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ParsedEngineerAddress {

    private String street;
    private String city;
    private String zip;
}
