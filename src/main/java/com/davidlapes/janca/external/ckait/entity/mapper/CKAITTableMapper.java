package com.davidlapes.janca.external.ckait.entity.mapper;

import com.davidlapes.janca.external.ckait.entity.CKAITEngineer;
import com.davidlapes.janca.external.ckait.entity.html.search.CKAITEngineerSearchTableRow;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CKAITTableMapper {

    public CKAITEngineer map( CKAITEngineerSearchTableRow row ) {
        CKAITEngineer engineer = new CKAITEngineer();

        engineer.setMemberId( row.getMemberId() );
        engineer.setDetailUrl( row.getDetailUrl() );
        engineer.setName( row.getName() );
        engineer.setAddress( row.getAddress() );
        engineer.setField( row.getField() );
        engineer.setSvobIng( row.getSvobIng() );

        return engineer;
    }
}
