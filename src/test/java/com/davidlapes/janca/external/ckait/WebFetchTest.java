package com.davidlapes.janca.external.ckait;

import com.davidlapes.janca.external.ckait.entity.CKAITEngineer;
import com.davidlapes.janca.external.ckait.entity.CKAITField;
import com.davidlapes.janca.external.ckait.entity.html.detail.CKAITEngineerDetailPage;
import com.davidlapes.janca.external.ckait.entity.html.detail.CKAITEngineerDetailTable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebFetchTest {

    @Test
    public void fetchAllTest() {
        Map<String, String> queryParams = new HashMap<>();

        queryParams.put( "tid", "40" );

        List<CKAITEngineer> engineers = CKAITEngineerService.getAllEngineers( queryParams );
        System.out.println( engineers.size() );
        //System.out.println(engineers);
    }

    @Test
    public void prepareSQL() {
        CKAITField.FIELDS_OF_ENGINEERING.keySet()
                .stream()
                .sorted()
                .forEach( field -> {
                    System.out.println(
                            "INSERT INTO fields(id, description) " +
                                    "VALUES('" + field + "', '" + CKAITField.FIELDS_OF_ENGINEERING.get( field ) + "')" +
                                    ";\n--;;" );
                } );
    }

    @Test
    public void fetchPage() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put( "tid", "40" );

        CKAITEngineerService.getEngineers( queryParams ).forEach( e -> System.out.println( e.toString() ) );
    }

    @Test
    public void fetchDetailPage() throws IOException {
        Document doc = Jsoup.connect( "https://www.ckait.cz/expert/form/0000057" ).get();

        CKAITEngineerDetailPage page = CKAITHtmlPageService.fetchEngineerDetailsPage( "https://www.ckait.cz/expert/form/0000004" );

        System.out.println(page.getEngineerDetail().toString());

        // System.out.println( doc.getElementById(  ) );
    }

    @Test
    public void detailPageWWW() {
        CKAITEngineerDetailPage page = CKAITHtmlPageService.fetchEngineerDetailsPage( "https://www.ckait.cz/expert/form/0000057" );
        CKAITEngineerDetailTable table = page.getDetailTable();
        System.out.println("asd".substring( 3 ));
        System.out.println(table.getCompanyWww());
    }

    @Test
    public void detailAll() {
        CKAITEngineerDetailPage page = CKAITHtmlPageService.fetchEngineerDetailsPage( "https://www.ckait.cz/expert/form/0000057" );
        CKAITEngineerDetailTable table = page.getDetailTable();

        System.out.println(table.getPersonalEmail());
        System.out.println(table.getPersonalPhone());
        System.out.println(table.getDateOfBirth().format( DateTimeFormatter.ofPattern( "dd.MM.yyyy" ) ));

        System.out.println(table.getCompanyName());
        System.out.println(table.getCompanyAddress());
        System.out.println(table.getCompanyPhone());
        System.out.println(table.getCompanyEmail());
        System.out.println(table.getCompanyIco());
        System.out.println(table.getCompanyWww());
    }
}
