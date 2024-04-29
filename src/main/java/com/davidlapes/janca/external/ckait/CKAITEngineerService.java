package com.davidlapes.janca.external.ckait;

import com.davidlapes.janca.external.ckait.entity.CKAITEngineer;
import com.davidlapes.janca.external.ckait.entity.CKAITEngineerDetail;
import com.davidlapes.janca.external.ckait.entity.html.detail.CKAITEngineerDetailPage;
import com.davidlapes.janca.external.ckait.entity.html.search.CKAITEngineerSearchPage;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class CKAITEngineerService {

    public CKAITEngineerDetail getDetail( String url ) {
        System.out.println( "Fetching " + url );
        CKAITEngineerDetailPage page = CKAITHtmlPageService.fetchEngineerDetailsPage( url );
        return page.getEngineerDetail();
    }

    public List<CKAITEngineer> getEngineers( Map<String, String> queryParams ) {
        CKAITEngineerSearchPage page = CKAITHtmlPageService.fetchEngineeringSearchPage( queryParams );
        return page.getEngineers();
    }

    public List<CKAITEngineer> getAllEngineers( Map<String, String> queryParams ) {
        Map<String, CKAITEngineer> engineers = new HashMap<>();

        if ( queryParams.containsKey( "page" ) && queryParams.get( "page" ) != null ) {
            return getEngineers( queryParams );
        }

        int lastPagination = CKAITHtmlPageService
                .fetchEngineeringSearchPage( queryParams )
                .getLastPagination();

        System.out.println( "Processing " + lastPagination + " pages...");

        for ( int currentPageNumber = 0; currentPageNumber < lastPagination; currentPageNumber++ ) {
            queryParams.put( "page", String.valueOf( currentPageNumber ) );

            try {
                Thread.sleep( 1000 );
            } catch ( InterruptedException e ) {
                throw new RuntimeException( e );
            }

            System.out.println( "Fetching page number " + currentPageNumber );

            List<CKAITEngineer> fetchedEngineers = getEngineers( queryParams );

            fetchedEngineers.forEach( engineer -> engineers.put( engineer.getMemberId(), engineer ) );
        }

        return engineers.values().stream().toList();
    }
}
