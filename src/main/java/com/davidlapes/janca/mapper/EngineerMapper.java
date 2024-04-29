package com.davidlapes.janca.mapper;

import com.davidlapes.janca.external.ckait.entity.CKAITEngineer;
import com.davidlapes.janca.external.ckait.entity.CKAITEngineerDetail;
import com.davidlapes.janca.model.Engineer;
import com.davidlapes.janca.model.Field;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class EngineerMapper {

    private ParsedEngineerName parseName( String name ) {
        Set<String> titles = new HashSet<>();

        if ( name.contains( "prof." ) || name.contains( "Prof." ) ) {
            name = name.replace( "prof.", "" );
            name = name.replace( "Prof.", "" );
            titles.add( "prof." );
        }

        if ( name.contains( "Ing." ) ) {
            name = name.replace( "Ing.", "" );
            titles.add( "Ing." );
        }

        if ( name.contains( "PhD." ) ) {
            name = name.replace( "Phd.", "" );
            titles.add( "PhD." );
        }

        if ( name.contains( "CSc." ) ) {
            name = name.replace( "CSc.", "" );
            titles.add( "CSc." );
        }

        if ( name.contains( "FEng." ) ) {
            name = name.replace( "FEng.", "" );
            titles.add( "FEng." );
        }

        if ( name.contains( "DrSc." ) ) {
            name = name.replace( "DrSc.", "" );
            titles.add( "DrSc." );
        }

        if ( name.contains( "Bc." ) ) {
            name = name.replace( "Bc.", "" );
            titles.add( "Bc." );
        }

        if ( name.contains( "doc." ) ) {
            name = name.replace( "doc.", "" );
            titles.add( "doc." );
        }

        if ( name.contains( "RNDr." ) ) {
            name = name.replace( "RNDr.", "" );
            titles.add( "RNDr." );
        }

        if ( name.contains( "Dr." ) ) {
            name = name.replace( "Dr.", "" );
            titles.add( "Dr." );
        }

        String titleBuilder = "";

        for( String title : titles) {
            titleBuilder += title + " ";
        }

        String[] nameParts = name.trim().replace( "  ", " " ).split( " " );

        String parsedTitle = titleBuilder.trim();
        String parsedFirstName = nameParts[0];
        String parsedLastName = nameParts[1];

        ParsedEngineerName parsedEngineerName = new ParsedEngineerName();
        parsedEngineerName.setFirstName( parsedFirstName.trim() );
        parsedEngineerName.setLastName( parsedLastName.trim() );
        parsedEngineerName.setTitle( parsedTitle.trim() );

        return parsedEngineerName;
    }

    private ParsedEngineerName parseName( CKAITEngineer engineer ) {
        return parseName( engineer.getName() );
    }

    private ParsedEngineerAddress parseAddress( String address ) {
        String regex = "\\d+/?\\w*";

        String zip = address.substring( address.length() - 1 - 5 );

        address = address.substring( 0, address.length() - 1 - 5 );

        String[] x = address.split( regex );

        String city = x[1];
        String street = address.replace( x[ 1 ], "" );

        ParsedEngineerAddress parsedEngineerAddress = new ParsedEngineerAddress();
        parsedEngineerAddress.setStreet( street.trim() );
        parsedEngineerAddress.setCity( city.trim() );
        parsedEngineerAddress.setZip( zip.trim() );

        return parsedEngineerAddress;
    }

    private ParsedEngineerAddress parseAddress( CKAITEngineer engineer ) {
        return parseAddress( engineer.getAddress() );
    }

    public Engineer map( CKAITEngineer sourceCore, CKAITEngineerDetail sourceDetail ) {
        Engineer target = new Engineer();
        target.setId( sourceCore.getMemberId() );

        ParsedEngineerName parsedEngineerName = parseName( sourceCore );
        ParsedEngineerAddress parsedEngineerAddress = parseAddress( sourceCore );

        target.setFirstName( parsedEngineerName.getFirstName() );
        target.setLastName( parsedEngineerName.getLastName() );
        target.setTitle( parsedEngineerName.getTitle() );
        target.setAddress( sourceCore.getAddress() );
        target.setStreet( parsedEngineerAddress.getStreet() );
        target.setCity( parsedEngineerAddress.getCity() );
        target.setZip( parsedEngineerAddress.getZip() );
        target.setSvobIng( sourceCore.getSvobIng() );
        target.setDetailUrl( sourceCore.getDetailUrl() );

        Set<Field> fields = Arrays
                .stream( sourceCore.getField().split( ", " ) )
                .collect( Collectors.toSet())
                .stream()
                .map( stringField -> {
                    Field field = new Field();
                    field.setId( stringField );
                    return field;
                } )
                .collect( Collectors.toSet());

        target.setFields( fields );

        if ( sourceDetail != null ) {

            target.setPersonalPhone( sourceDetail.getPersonalPhone() );
            target.setPersonalEmail( sourceDetail.getPersonalEmail() );
            target.setDateOfBirth( sourceDetail.getDateOfBirth() );
            target.setCompanyName( sourceDetail.getCompanyName() );
            target.setCompanyAddress( sourceDetail.getCompanyAddress() );
            target.setCompanyEmail( sourceDetail.getCompanyEmail() );
            target.setCompanyPhone( sourceDetail.getCompanyPhone() );
            target.setCompanyWww( sourceDetail.getCompanyWww() );
            target.setCompanyIco( sourceDetail.getCompanyIco() );
        }

        return target;
    }
}
