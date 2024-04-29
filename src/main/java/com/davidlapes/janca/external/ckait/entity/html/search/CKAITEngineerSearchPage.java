package com.davidlapes.janca.external.ckait.entity.html.search;

import com.davidlapes.janca.external.ckait.entity.CKAITEngineer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CKAITEngineerSearchPage {

    private CKAITEngineerSearchTable table;
    private List<CKAITEngineer> engineers;

    public CKAITEngineerSearchPage( CKAITEngineerSearchTable table, List<CKAITEngineer> engineers ) {
        this.table = table;
        this.engineers = engineers;
    }

    public int getLastPagination() {
        return table.getLastPaginationNumber();
    }
}
