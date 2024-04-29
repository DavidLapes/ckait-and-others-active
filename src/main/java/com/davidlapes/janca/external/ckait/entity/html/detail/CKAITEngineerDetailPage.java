package com.davidlapes.janca.external.ckait.entity.html.detail;

import com.davidlapes.janca.external.ckait.entity.CKAITEngineerDetail;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class CKAITEngineerDetailPage {

    private CKAITEngineerDetailTable detailTable;
    private CKAITEngineerDetail engineerDetail;

    public CKAITEngineerDetailPage(CKAITEngineerDetailTable detailTable, CKAITEngineerDetail engineerDetail) {
        this.detailTable = detailTable;
        this.engineerDetail = engineerDetail;
    }
}
