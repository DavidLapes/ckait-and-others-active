package com.davidlapes.janca.external.ckait;

import com.davidlapes.janca.external.ckait.entity.html.detail.CKAITEngineerDetailPage;
import com.davidlapes.janca.external.ckait.entity.html.detail.CKAITEngineerDetailParser;
import com.davidlapes.janca.external.ckait.entity.html.search.CKAITEngineerSearchPage;
import com.davidlapes.janca.external.ckait.entity.html.search.CKAITEngineerSearchParser;
import com.davidlapes.janca.external.ckait.http.CKAITRequestBuilder;
import lombok.experimental.UtilityClass;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

@UtilityClass
public class CKAITHtmlPageService {

    public CKAITEngineerSearchPage fetchEngineeringSearchPage( Map<String, String> queryParams ) {
        try {
            URL url = CKAITRequestBuilder.buildEngineeringSearchUrl( queryParams );
            Connection connection = Jsoup.connect( url.toString() );
            Document document = connection.get();
            return CKAITEngineerSearchParser.getSearchPage( document );
        } catch ( IOException ioEx ) {
            throw new RuntimeException( ioEx );
        }
    }

    public CKAITEngineerDetailPage fetchEngineerDetailsPage( String url ) {
        try {
            Connection connection = Jsoup.connect( url );
            Document document = connection.get();
            return CKAITEngineerDetailParser.getDetailPage( document );
        } catch ( IOException ioEx ) {
            throw new RuntimeException( ioEx );
        }
    }
}
