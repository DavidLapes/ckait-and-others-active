package com.davidlapes.janca.external.ckait.entity.html.search;

import lombok.Getter;

import java.util.List;

@Getter
public class CKAITEngineerSearchTableHeader {

    private final List<String> columns;

    public CKAITEngineerSearchTableHeader( List<String> columns ) {
        this.columns = columns;
    }
}
