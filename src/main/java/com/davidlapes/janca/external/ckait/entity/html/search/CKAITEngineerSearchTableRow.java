package com.davidlapes.janca.external.ckait.entity.html.search;

import lombok.Getter;
import org.jsoup.nodes.Element;

import java.util.List;

import static com.davidlapes.janca.external.ckait.entity.html.search.CKAITEngineerSearchTableStructure.*;
import static com.davidlapes.janca.external.ckait.http.CKAITConfig.CKAIT_HOST;
import static com.davidlapes.janca.external.ckait.http.CKAITConfig.CKAIT_SCHEME;

@Getter
public class CKAITEngineerSearchTableRow {

    private final List<Element> columns;

    public CKAITEngineerSearchTableRow( List<Element> columns ) {
        this.columns = columns;
    }

    public String getMemberId() {
        return columns.get( MEMBER_ID_INDEX ).text();
    }

    public String getDetailUrl() {
        Element element = columns.get( DETAIL_URL_INDEX );

        if ( ! element.children().isEmpty() ) {
            return CKAIT_SCHEME + "://" + CKAIT_HOST + element.firstElementChild().attribute( "href" ).getValue();
        } else {
            return null;
        }
    }

    public String getName() {
        return columns.get( NAME_INDEX ).text();
    }

    public String getAddress() {
        return columns.get( ADDRESS_INDEX ).text();
    }

    public String getSvobIng() {
        return columns.get( SVOB_ING_INDEX ).text();
    }

    public String getField() {
        return columns.get( FIELD_INDEX ).text();
    }
}
