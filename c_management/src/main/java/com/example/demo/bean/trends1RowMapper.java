package com.example.demo.bean;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class trends1RowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        trend1 user=new trend1();

        user.setId(resultSet.getInt("count(id)"));
        user.setCity(resultSet.getString("city"));

        return user;
    }
}
