package com.davidlapes.janca.external.ckait.entity;

import java.util.Map;

import static java.util.Map.entry;

public class CKAITField {

    public static final Map<String, String> FIELDS_OF_ENGINEERING = Map.ofEntries(
            entry( "IA00", "energetické auditorství" ),
            entry( "ID00", "dopravní stavby" ),
            entry( "IE00", "technika prostředí staveb" ),
            entry( "IE01", "technika prostředí staveb - technická zařízení" ),
            entry( "IE02", "technika prostředí staveb - elektrotechnická zařízení" ),
            entry( "IG00", "geotechnika" ),
            entry( "IH00", "požární bezpečnost staveb" ),
            entry( "II00", "městské inženýrství" ),
            entry( "IL00", "stavby pro plnění funkce lesa" ),
            entry( "IM00", "mosty a inženýrské funkce" ),
            entry( "IP00", "pozemní stavby" ),
            entry( "IS00", "statika a dynamika staveb" ),
            entry( "IT00", "technologická zařízení staveb" ),
            entry( "IV00", "stavby vodního hospodářství a krajinného inženýrství" ),
            entry( "IZ00", "zkoušení a diagnostika staveb" ),
            entry( "SD01", "dopravní stavby - kolejová doprava" ),
            entry( "SD02", "dopravní stavby - nekolejová doprava" ),
            entry( "SM00", "mosty a inženýrské konstrukce" ),
            entry( "SP00", "pozemní stavby" ),
            entry( "SV01", "stavby vodního hospodářství a krajinného inženýrství - stavby hydrotechnické" ),
            entry( "SV02", "stavby vodního hospodářství a krajinného inženýrství - stavby zdravotnětechnické" ),
            entry( "SV03", "stavby vodního hospodářství a krajinného inženýrství - stavby meliorační a sanační" ),
            entry( "TA00", "energetické auditorství" ),
            entry( "TD01", "dopravní stavby - kolejová doprava" ),
            entry( "TD02", "dopravní stavby - nekolejová doprava" ),
            entry( "TE01", "technika prostředí staveb - vytápění a vzduchotecnika" ),
            entry( "TE02", "technika prostředí staveb - zdravotní technika" ),
            entry( "TE03", "technika prostředí staveb - elektrotechnická zařízení" ),
            entry( "TG00", "geotechnika" ),
            entry( "TH00", "požární bezpečnost staveb" ),
            entry( "TM00", "mosty a inženýrské konstrukce" ),
            entry( "TP00", "pozemní stavby" ),
            entry( "TT00", "technologická zařízení staveb" ),
            entry( "TV01", "stavby vodního hospodářství a krajinného inženýrství - stavby hydrotechnické" ),
            entry( "TV02", "stavby vodního hospodářství a krajinného inženýrství - stavby zdravotnětechnické" ),
            entry( "TV03", "stavby vodního hospodářství a krajinného inženýrství - stavby meliorační a sanační" )
    );

    public static String getByKey( String key ) {
        return FIELDS_OF_ENGINEERING.get( key );
    }
}
