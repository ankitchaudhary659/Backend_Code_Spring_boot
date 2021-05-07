package com.example.demo.bean;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class trend2RowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        trend2 user=new trend2();

        user.setID(resultSet.getInt("count(id)"));
        user.setLocation(resultSet.getString("location"));

        return user;
    }
}