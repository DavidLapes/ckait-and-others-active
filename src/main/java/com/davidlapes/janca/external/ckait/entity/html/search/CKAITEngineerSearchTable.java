package com.davidlapes.janca.external.ckait.entity.html.search;

import lombok.Getter;

import java.util.List;

@Getter
public class CKAITEngineerSearchTable {

    private final CKAITEngineerSearchTableHeader header;
    private final List<CKAITEngineerSearchTableRow> rows;
    private final int lastPaginationNumber;

    public CKAITEngineerSearchTable(
            CKAITEngineerSearchTableHeader header,
            List<CKAITEngineerSearchTableRow> rows,
            int lastPaginationNumber
    ) {
        this.header = header;
        this.rows = rows;
        this.lastPaginationNumber = lastPaginationNumber;
    }
}
