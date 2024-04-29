package com.davidlapes.janca.external.ckait.entity.html.detail;

import com.davidlapes.janca.external.ckait.entity.CKAITEngineerDetail;
import lombok.experimental.UtilityClass;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import static com.davidlapes.janca.external.ckait.entity.html.detail.CKAITEngineerDetailTableStructure.*;

@UtilityClass
public class CKAITEngineerDetailParser {

    public Element getPersonalEmail( Document document ) {
        return document.getElementById( PERSONAL_EMAIL_ID );
    }

    public Element getPersonalPhone( Document document ) {
        return document.getElementById( PERSONAL_PHONE_ID );
    }

    public Element getDateOfBirth( Document document ) {
        return document.getElementById( DATE_OF_BIRTH_ID );
    }

    public Element getCompanyTable( Document document ) {
        return document.getElementById( COMPANY_TABLE_ID ).lastElementChild();
    }

    public Element getCompanyWww( Document document ) {
        return getCompanyTable( document ).child( COMPANY_WWW_INDEX );
    }

    public Element getCompanyIco( Document document ) {
        return getCompanyTable( document ).child( COMPANY_ICO_INDEX );
    }

    public Element getCompanyName( Document document ) {
        return document.getElementById( COMPANY_NAME_ID );
    }

    public Element getCompanyEmail( Document document ) {
        return getCompanyTable( document ).child( COMPANY_EMAIL_INDEX );
    }

    public Element getCompanyPhone( Document document ) {
        return getCompanyTable( document ).child( COMPANY_PHONE_INDEX );
    }

    public Element getCompanyStreet( Document document ) {
        return document.getElementById( COMPANY_STREET_ID );
    }

    public Element getCompanyCity( Document document ) {
        return document.getElementById( COMPANY_CITY_ID );
    }

    public Element getCompanyZip( Document document ) {
        return document.getElementById( COMPANY_ZIP );
    }

    public CKAITEngineerDetailTableElements getDetailElements( Document document ) {
        CKAITEngineerDetailTableElements elements = new CKAITEngineerDetailTableElements();

        elements.setPersonalEmail( getPersonalEmail( document ) );
        elements.setPersonalPhone( getPersonalPhone( document ) );
        elements.setDateOfBirth( getDateOfBirth( document ) );
        elements.setCompanyName( getCompanyName( document ) );
        elements.setCompanyCity( getCompanyCity( document ) );
        elements.setCompanyStreet( getCompanyStreet( document ) );
        elements.setCompanyZip( getCompanyZip( document ) );
        elements.setCompanyEmail( getCompanyEmail( document ) );
        elements.setCompanyPhone( getCompanyPhone( document ) );
        elements.setCompanyWww( getCompanyWww( document ) );
        elements.setCompanyIco( getCompanyIco( document ) );

        return  elements;
    }

    public CKAITEngineerDetailTable getDetailTable( Document document ) {
        return new CKAITEngineerDetailTable( getDetailElements( document ) );
    }

    public CKAITEngineerDetail getEngineerDetail( Document document ) {
        CKAITEngineerDetailTable detailTable = getDetailTable( document );

        CKAITEngineerDetail detail = new CKAITEngineerDetail();

        detail.setPersonalEmail( detailTable.getPersonalEmail() );
        detail.setPersonalPhone( detailTable.getPersonalPhone() );
        detail.setDateOfBirth( detailTable.getDateOfBirth() );

        detail.setCompanyName( detailTable.getCompanyName() );
        detail.setCompanyAddress( detailTable.getCompanyAddress() );
        detail.setCompanyEmail( detailTable.getCompanyEmail() );
        detail.setCompanyPhone( detailTable.getCompanyPhone() );
        detail.setCompanyWww( detailTable.getCompanyWww() );
        detail.setCompanyIco( detailTable.getCompanyIco() );

        return detail;
    }

    public CKAITEngineerDetailPage getDetailPage( Document document ) {
        CKAITEngineerDetail engineerDetail = getEngineerDetail( document );
        CKAITEngineerDetailTable detailTable = getDetailTable( document );

        return new CKAITEngineerDetailPage(detailTable, engineerDetail);
    }
}
