package com.davidlapes.janca.external.ckait.entity.html.search;

import com.davidlapes.janca.external.ckait.entity.CKAITEngineer;
import com.davidlapes.janca.external.ckait.entity.mapper.CKAITTableMapper;
import lombok.experimental.UtilityClass;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@UtilityClass
public class CKAITEngineerSearchParser {

    public Element getBody( Document document ) {
        return document.body();
    }

    public Element getLastPagination( Document document ) {
        return document.getElementsByClass( "pager__item pager__item--last" )
                .first()
                .firstElementChild();
    }

    public String getLastPaginationUrl( Document document ) {
        Element lastPaginationElement = getLastPagination( document );

        return lastPaginationElement.attribute( "href" ).getValue();
    }

    public int getLastPaginationNumber( Document document ) {
        String url = getLastPaginationUrl( document );

        MultiValueMap<String, String> queryParams = UriComponentsBuilder
                .fromUriString( url )
                .build()
                .getQueryParams();

        String pageParam = queryParams.getFirst( "page" );

        return Integer.parseInt( Objects.requireNonNull( pageParam ) );
    }

    public Element getTableElement( Document document ) {
        return getBody( document )
                .getElementById( "block-journaleight-content" )
                .firstElementChild()
                .firstElementChild()
                .firstElementChild()
                .getElementsByClass( "view-content" )
                .first();
    }

    public Element getTableHeaderElement( Document document ) {
        return getTableElement( document )
                .firstElementChild()
                .children()
                .first();
    }

    public Element getTableBodyElement( Document document ) {
        return getTableElement( document )
                .firstElementChild()
                .children()
                .last();
    }

    public CKAITEngineerSearchTableHeader getTableHeaders( Document document ) {
        List<Element> elements = getTableHeaderElement( document )
                .firstElementChild()
                .children();

        List<String> headers = elements.stream()
                .map( Element::text )
                .toList();

        return new CKAITEngineerSearchTableHeader( headers );
    }

    public List<CKAITEngineerSearchTableRow> getTableRows( Document document ) {
        List<Element> rows = getTableBodyElement( document ).children();

        return rows.stream()
                .map( rowColumns -> new CKAITEngineerSearchTableRow( rowColumns.children() ) )
                .collect( Collectors.toList() );
    }

    public CKAITEngineerSearchTable getTable( Document document ) {
        CKAITEngineerSearchTableHeader header = getTableHeaders( document );
        List<CKAITEngineerSearchTableRow> rows = getTableRows( document );
        int lastPagination = getLastPaginationNumber( document );

        return new CKAITEngineerSearchTable( header, rows, lastPagination );
    }

    public List<CKAITEngineer> getEngineers( Document document ) {
        List<CKAITEngineerSearchTableRow> rows = getTableRows( document );

        return rows.stream()
                .map( CKAITTableMapper::map )
                .toList();
    }

    public CKAITEngineerSearchPage getSearchPage( Document document ) {
        CKAITEngineerSearchTable table = getTable( document );
        List<CKAITEngineer> engineers = getEngineers( document );
        return new CKAITEngineerSearchPage( table, engineers );
    }
}
