package com.davidlapes.janca.external.ckait.entity.html.detail;

import lombok.Getter;
import lombok.Setter;
import org.jsoup.nodes.Element;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class CKAITEngineerDetailTable {

    private final CKAITEngineerDetailTableElements detailTableElements;

    public CKAITEngineerDetailTable( CKAITEngineerDetailTableElements detailTableElements ) {
        this.detailTableElements = detailTableElements;
    }

    public String getPersonalEmail() {
        Element email = detailTableElements.getPersonalEmail();

        if ( email == null ) {
            return null;
        }

        if ( email.text().length() > "E-mail".length() ) {
            return email.text().substring( "E-mail".length() ).replace( " ", "" );
        } else {
            return null;
        }
    }

    public String getPersonalPhone() {
        Element phone = detailTableElements.getPersonalPhone();

        if ( phone == null ) {
            return null;
        }

        if ( phone.text().length() > "Telefon".length() ) {
            return phone.text().substring( "Telefon".length() ).replace( " ", "" );
        } else {
            return null;
        }
    }

    public LocalDate getDateOfBirth() {
        Element dateOfBirth = detailTableElements.getDateOfBirth();

        if ( dateOfBirth == null ) {
            return null;
        }

        if ( dateOfBirth.text().length() > "Datum narození".length() ) {
            String date = dateOfBirth.text().substring( "Datum narození".length() ).replace( " ", "" );
            return LocalDate.parse( date, DateTimeFormatter.ofPattern( "dd.MM.yyyy" ) );
        } else {
            return null;
        }
    }

    public String getCompanyName() {
        Element name = detailTableElements.getCompanyName();

        if ( name == null ) {
            return null;
        }

        if ( name.text().length() > "Jméno firmy".length() ) {
            return name.text().substring( "Jméno firmy".length() ).trim();
        } else {
            return null;
        }
    }

    public String getCompanyAddress() {
        Element street = detailTableElements.getCompanyStreet();
        String streetString = null;
        if ( street != null ) {
            streetString = street.text();
        }
        if ( streetString != null ) {
            streetString = streetString.replace( "Ulice", "" ).trim();
        }

        Element city = detailTableElements.getCompanyCity();
        String cityString = null;
        if ( city != null ) {
            cityString = city.text();
        }
        if ( cityString != null ) {
            cityString = cityString.replace( "Obec", "" ).trim();
        }

        Element zip = detailTableElements.getCompanyZip();
        String zipString = null;
        if ( zip != null ) {
            zipString = zip.text();
        }
        if ( zipString != null ) {
            zipString = zipString.replace( "PSČ", "" ).trim();
        }

        String addressString = "";

        if ( streetString != null ) {
            addressString += streetString;
        }

        if ( cityString != null ) {
            addressString += " " + cityString;
        }

        if ( zipString != null ) {
            addressString += " " + zipString;
        }

        return addressString;
    }

    public String getCompanyPhone() {
        Element phone = detailTableElements.getCompanyPhone();

        if ( phone == null ) {
            return null;
        }

        if ( phone.text().length() > "Telefon".length() ) {
            return phone.text().substring( "Telefon".length() ).replace( " ", "" );
        } else {
            return null;
        }
    }

    public String getCompanyEmail() {
        Element email = detailTableElements.getCompanyEmail();

        if ( email == null ) {
            return null;
        }

        if ( email.text().length() > "E-mail".length() ) {
            return email.text().substring( "E-mail".length() ).replace( " ", "" );
        } else {
            return null;
        }
    }

    public String getCompanyIco() {
        Element ico = detailTableElements.getCompanyIco();

        if ( ico == null ) {
            return null;
        }

        if ( ico.text().length() > "IČ".length() ) {
            return ico.text().substring( "IČ".length() ).replace( " ", "" );
        } else {
            return null;
        }
    }

    public String getCompanyWww() {
        Element www = detailTableElements.getCompanyWww();

        if ( www == null ) {
            return null;
        }

        if ( www.text().length() > "www".length() ) {
            return www.text().substring( "www".length() ).replace( " ", "" );
        } else {
            return null;
        }
    }
}
