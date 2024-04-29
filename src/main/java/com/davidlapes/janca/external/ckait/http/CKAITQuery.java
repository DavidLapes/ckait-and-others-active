package com.davidlapes.janca.external.ckait.http;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class CKAITQuery {

    public static final String TITLE = "title";
    public static final String FIELD_FIRSTNAME_VALUE = "field_firstname_value";
    public static final String FIELD_SURNAME_VALUE = "field_surname_value";
    public static final String FIELD_SPEC_NID_OP = "field_spec_nid_op";
    public static final String TID_2 = "tid_2";
    public static final String TID = "tid";
    public static final String PAGE = "page";

    public static Map<String, String> withDefaultQueryParams( Map<String, String> queryParams ) {
        if ( queryParams == null ) {
            queryParams = new HashMap<>();
        }

        Map<String, String> defaultQueryParams = Map.ofEntries(
                entry( TITLE, "" ),
                entry( FIELD_FIRSTNAME_VALUE, "" ),
                entry( FIELD_SURNAME_VALUE, "" ),
                entry( FIELD_SPEC_NID_OP, "or" ),
                entry( TID_2, "All" ),
                entry( TID, "All" ),
                entry( PAGE, "0" )
        );

        Map<String, String> mergedQueryParams = new HashMap<>();

        mergeQueryParam( TITLE, defaultQueryParams, queryParams, mergedQueryParams );
        mergeQueryParam( FIELD_FIRSTNAME_VALUE, defaultQueryParams, queryParams, mergedQueryParams );
        mergeQueryParam( FIELD_SURNAME_VALUE, defaultQueryParams, queryParams, mergedQueryParams );
        mergeQueryParam( FIELD_SPEC_NID_OP, defaultQueryParams, queryParams, mergedQueryParams );
        mergeQueryParam( TID_2, defaultQueryParams, queryParams, mergedQueryParams );
        mergeQueryParam( TID, defaultQueryParams, queryParams, mergedQueryParams );
        mergeQueryParam( PAGE, defaultQueryParams, queryParams, mergedQueryParams );

        return mergedQueryParams;
    }

    private static void mergeQueryParam(
            String key,
            Map<String, String> defaultQueryParams,
            Map<String, String> optionalQueryParams,
            Map<String, String> mergedQueryParams
    ) {
        if ( optionalQueryParams.containsKey( key ) ) {
            mergedQueryParams.put( key, optionalQueryParams.get( key ) );
        } else {
            mergedQueryParams.put( key, defaultQueryParams.get( key ) );
        }
    }
}
