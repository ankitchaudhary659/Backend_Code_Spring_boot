package com.example.demo.bean;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class eduDetailsRowMapper  implements RowMapper {

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        eduDetails edu=new eduDetails();

        edu.setId(resultSet.getInt("id"));
        edu.setCourse(resultSet.getString("course"));
        edu.setClgname(resultSet.getString("clgname"));
        edu.setUniversityname(resultSet.getString("universityname"));
        edu.setAddress(resultSet.getString("address"));

        return edu;
    }
}