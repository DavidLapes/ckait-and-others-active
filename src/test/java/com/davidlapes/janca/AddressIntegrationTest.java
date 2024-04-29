package com.davidlapes.janca;

import com.davidlapes.janca.model.Engineer;
import com.davidlapes.janca.repository.EngineerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RunWith( SpringRunner.class )
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ProjectBoot.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class AddressIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private EngineerRepository repository;

    @Test
    public void loadEngineers() {
        List<Engineer> engineers = repository.findAll();

        System.out.println("There is " + engineers.size() + " engineers!");

        String regex = "\\d+/?\\w+";

        engineers.subList( 0, 20 ).forEach( engineer -> {

            String address = engineer.getAddress();

            String zip = address.substring( address.length() - 1 - 5 );

            address = address.substring( 0, address.length() - 1 - 5 );

            String[] x = address.split( regex );

            String city = x[1];
            String street = address.replace( x[ 1 ], "" ).trim();
        } );

        System.out.println("And that's it!");
    }
}
