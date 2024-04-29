package com.davidlapes.janca.service.excel;

import com.davidlapes.janca.mapper.EngineerRowMapper;
import com.davidlapes.janca.model.Engineer;
import com.davidlapes.janca.model.Field;
import com.davidlapes.janca.service.EngineerService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ExcelService {

    private final EngineerService service;
    private final DataSource dataSource;

    @Autowired
    public ExcelService( EngineerService service, DataSource dataSource ) {
        this.service = service;
        this.dataSource = dataSource;
    }

    // TODO: Move
    public List<Engineer> getProperEngineers() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate( dataSource );

        // TODO: Clean this up
        List<String> fields = Arrays.stream( new String[]{ "SP00", "SM00", "IS00", "ID00", "IT00" } ).toList();

        RowMapper<Engineer> mapper = new EngineerRowMapper();

        String sql = "SELECT member_id FROM engineers_fields WHERE field_id IN ( 'SP00', 'SM00', 'IS00', 'ID00', 'IT00' )";

        List<Engineer> engineers = jdbcTemplate.query( sql, mapper );

        return engineers.stream().map( engineer -> service.getById( engineer.getId() ) ).toList();
    }

    public void writeEngineers() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        List<Engineer> engineers = getProperEngineers();

        // TODO: Don't rewrite
        //AtomicInteger rowIndexCounter = new AtomicInteger( 149 );
        AtomicInteger rowIndexCounter = new AtomicInteger( 0 );

        engineers.forEach( engineer -> {

            Row row = sheet.createRow( rowIndexCounter.get() );

            Cell titleCell = row.createCell( 0 );
            titleCell.setCellValue( engineer.getTitle() );

            Cell nameCell = row.createCell( 1 );
            nameCell.setCellValue( engineer.getFirstName() );

            Cell surnameCell = row.createCell( 2 );
            surnameCell.setCellValue( engineer.getLastName() );

            Cell companyNameCell = row.createCell( 3 );
            companyNameCell.setCellValue( engineer.getCompanyName() );

            String phone = engineer.getPersonalPhone();
            if ( phone != null && ! phone.isBlank() ) {
                String tmpPhoneOriginal = phone;
                String tmpPhone = "";

                if ( phone.length() == 13 ) {
                    tmpPhoneOriginal = tmpPhoneOriginal.substring( 4 );
                    tmpPhone = "420 ";
                    tmpPhone += tmpPhoneOriginal.substring( 0, 3 ) + " ";
                    tmpPhone += tmpPhoneOriginal.substring( 3, 6 ) + " ";
                    tmpPhone += tmpPhoneOriginal.substring( 6 );
                }

                if ( phone.length() == 9 ) {
                    tmpPhone = "420 ";
                    tmpPhone += tmpPhoneOriginal.substring( 0, 3 ) + " ";
                    tmpPhone += tmpPhoneOriginal.substring( 3, 6 ) + " ";
                    tmpPhone += tmpPhoneOriginal.substring( 6 );
                }

                phone = tmpPhone;

            }
            Cell personalPhoneCell = row.createCell( 4 );
            personalPhoneCell.setCellValue( phone );

            Cell personalEmailCell = row.createCell( 5 );
            personalEmailCell.setCellValue( engineer.getPersonalEmail() );

            Cell companyWwwCell = row.createCell( 6 );
            companyWwwCell.setCellValue( engineer.getCompanyWww() );

            Cell personalAddressCell = row.createCell( 7 );
            personalAddressCell.setCellValue( engineer.getAddress() );

            Cell personalAddressCityCell = row.createCell( 8 );
            personalAddressCityCell.setCellValue( engineer.getCity() );

            Cell emptyCell = row.createCell( 9 );
            emptyCell.setCellValue( "" );

            Cell noteCell = row.createCell( 10 );
            String field = "";
            for ( Field f : engineer.getFields() ) {
                field += f.getDescription() + " ";
            }
            noteCell.setCellValue( field );

            rowIndexCounter.getAndIncrement();
        } );

        File currDir = new File( "." );
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring( 0, path.length() - 1 ) + "Databáze.xlsx";

        FileOutputStream outputStream = new FileOutputStream( fileLocation );

        workbook.write( outputStream );
        workbook.close();

        outputStream.close();
    }
}
