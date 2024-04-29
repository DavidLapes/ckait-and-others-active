package com.davidlapes.janca.mapper;

import com.davidlapes.janca.model.Engineer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EngineerRowMapper implements RowMapper<Engineer> {

    @Override
    public Engineer mapRow( ResultSet rs, int rowNum ) throws SQLException {
        Engineer engineer = new Engineer();

        engineer.setId( rs.getString( "member_id" ) );

        return engineer;
    }
}
