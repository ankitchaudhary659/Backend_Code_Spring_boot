package com.example.demo.bean;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CandidateRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Candidate_details candidates=new Candidate_details();
        candidates.setId(resultSet.getInt("id"));
        candidates.setFirstname(resultSet.getString("firstname"));
        candidates.setLastname(resultSet.getString("lastname"));
        candidates.setPhoneno(resultSet.getString("phoneno"));
        candidates.setEmail(resultSet.getString("email"));
        candidates.setBirthdate(resultSet.getString("birthdate"));
        candidates.setCity(resultSet.getString("city"));
        candidates.setCountry(resultSet.getString("country"));

        return candidates;
    }
}