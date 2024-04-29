package com.davidlapes.janca.external.ckait.http;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import static com.davidlapes.janca.external.ckait.http.CKAITConfig.*;
import static com.davidlapes.janca.external.ckait.http.CKAITQuery.*;

public class CKAITRequestBuilder {

    public static URL buildEngineeringSearchUrl( Map<String, String> queryParams ) throws MalformedURLException {
        Map<String, String> mergedQueryParams = CKAITQuery.withDefaultQueryParams( queryParams );

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance()
                .scheme( CKAIT_SCHEME )
                .host( CKAIT_HOST )
                .path( CKAIT_AUTHORIZED_ENGINEERS_PATH );

        uriBuilder.queryParam( TITLE, mergedQueryParams.get( TITLE ) );
        uriBuilder.queryParam( FIELD_FIRSTNAME_VALUE, mergedQueryParams.get( FIELD_FIRSTNAME_VALUE ) );
        uriBuilder.queryParam( FIELD_SURNAME_VALUE, mergedQueryParams.get( FIELD_SURNAME_VALUE ) );
        uriBuilder.queryParam( FIELD_SPEC_NID_OP, mergedQueryParams.get( FIELD_SPEC_NID_OP ) );
        uriBuilder.queryParam( TID_2, mergedQueryParams.get( TID_2 ) );
        uriBuilder.queryParam( TID, mergedQueryParams.get( TID ) );
        uriBuilder.queryParam( PAGE, mergedQueryParams.get( PAGE ) );

        return uriBuilder.build().toUri().toURL();
    }
}
