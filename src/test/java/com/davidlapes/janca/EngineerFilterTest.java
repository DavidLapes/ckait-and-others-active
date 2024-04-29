package com.davidlapes.janca;

import com.davidlapes.janca.mapper.EngineerRowMapper;
import com.davidlapes.janca.model.Engineer;
import com.davidlapes.janca.model.Field;
import com.davidlapes.janca.model.district.Psc;
import com.davidlapes.janca.repository.EngineerRepository;
import com.davidlapes.janca.repository.PscRepository;
import com.davidlapes.janca.service.EngineerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith( SpringRunner.class )
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ProjectBoot.class
)
@AutoConfigureMockMvc
@TestPropertySource( locations = "classpath:application.properties" )
public class EngineerFilterTest {

    @Autowired
    private EngineerService service;

    @Autowired
    private PscRepository pscRepository;

    @Autowired
    private EngineerRepository engineerRepository;

    @Autowired
    public DataSource dataSource;

    @Test
    public void jdbcTest() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate( dataSource );

        String sql = "SELECT member_id FROM engineers_fields WHERE field_id IN ( 'SP00', 'SM00', 'IS00', 'ID00', 'IT00' )";

        List<Engineer> engineers = jdbcTemplate.query( sql, new EngineerRowMapper() );

        System.out.println( engineers.size() );
    }

    @Test
    public void matchWithOkresTest() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate( dataSource );

        String sql = "SELECT member_id FROM engineers_fields WHERE field_id IN ( 'SP00', 'SM00', 'IS00', 'ID00', 'IT00' )";

        List<Engineer> engineers = jdbcTemplate.query( sql, new EngineerRowMapper() );

        List<Engineer> detailedEngineers = new ArrayList<>();

        engineers.forEach( eng -> detailedEngineers.add( engineerRepository.findById( eng.getId() ).get() ) );

        for ( Engineer eng : detailedEngineers ) {
            Psc psc = pscRepository.findByPscAndName( eng.getZip(), eng.getCity() );
            if ( psc == null ) {
                System.out.println(eng.getCity());
                System.out.println( eng.getZip() );
                System.out.println("---------------------------------------");
            }
        }
    }

    @Test
    public void filterByFields() {
        // TODO: Clean this up
        List<Field> fields = new ArrayList<>();

        Field fieldSP00 = new Field();
        fieldSP00.setId( "SP00" );

        Field fieldSM00 = new Field();
        fieldSM00.setId( "SM00" );

        Field fieldIS00 = new Field();
        fieldIS00.setId( "IS00" );

        Field fieldID00 = new Field();
        fieldID00.setId( "ID00" );

        Field fieldIT00 = new Field();
        fieldIT00.setId( "IT00" );

        fields.add( fieldSP00 );
        fields.add( fieldSM00 );
        fields.add( fieldIS00 );
        fields.add( fieldID00 );
        fields.add( fieldIT00 );

        // TODO: Filtering by fields
        List<Engineer> allEngineers = service.getAll();

        System.out.println( "There is: " + allEngineers.size() + " engineers..." );

        // TODO: Better naming
        List<Engineer> engineers = allEngineers
                .stream()
                .filter( engineer -> {
                    System.out.println( engineer.getFields().stream().map( Field::getId ).toList() );
                    ;
                    System.out.println( !Collections.disjoint( engineer.getFields(), fields ) );
                    return !Collections.disjoint( engineer.getFields(), fields );
                } )
                .toList();

        System.out.println( "Filtered result has: " + engineers.size() + " engineers..." );
    }
}
