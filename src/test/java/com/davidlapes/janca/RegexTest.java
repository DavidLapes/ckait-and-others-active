package com.davidlapes.janca;

import com.davidlapes.janca.mapper.ParsedEngineerAddress;
import org.junit.Test;

public class RegexTest {

    private ParsedEngineerAddress parseAddress( String address ) {
        String regex = "\\d+/?\\w*";

        String zip = address.substring( address.length() - 1 - 5 );

        address = address.substring( 0, address.length() - 1 - 5 );

        String[] x = address.split( regex );

        String city = x[ 1 ];
        String street = address.replace( x[ 1 ], "" ).trim();

        ParsedEngineerAddress parsedEngineerAddress = new ParsedEngineerAddress();
        parsedEngineerAddress.setStreet( street );
        parsedEngineerAddress.setCity( city );
        parsedEngineerAddress.setZip( zip );

        return parsedEngineerAddress;
    }

    @Test
    public void regex() {
        String[] addresses = new String[]{
                "Veveké Knínice 5 Veverské Knínice"
        };

        System.out.println( "------------------" );
        for ( String s : addresses ) {
            System.out.println( parseAddress( s ) );
        }
    }
}
