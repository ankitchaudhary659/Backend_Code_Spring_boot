package com.example.demo.repository;


import com.example.demo.bean.eduDetails;
import com.example.demo.bean.eduDetailsRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;


@Repository
public class eduDetailsRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<eduDetails> get_eduDetails(){
        return  jdbcTemplate.query("select * from eduDetails_table",new eduDetailsRowMapper());
    }

    public eduDetails find_ById(Integer id){

        String sql = "SELECT * FROM edudetails_table WHERE ID = ?";
        try{
            return (eduDetails) this.jdbcTemplate.queryForObject(
                    sql, new Object[] { id }, new eduDetailsRowMapper());
        }
        catch(EmptyResultDataAccessException ex){
            return null;
        }

    }

    public Boolean save_eduDetails(eduDetails eduDetails){
        String query="insert into edudetails_table values(?,?,?,?,?)";
        return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {

                ps.setInt(1,eduDetails.getId());
                ps.setString(2,eduDetails.getClgname());
                ps.setString(3,eduDetails.getUniversityname());
                ps.setString(4,eduDetails.getCourse());
                ps.setString(5,eduDetails.getAddress());



                return ps.execute();

            }
        });
    }

    public Integer update_eduDetails(eduDetails eduDetails){
        String query="update edudetails_table set clgname = ? , universityname = ? , course = ? , address = ? where id = ?";
        Object[] params = {eduDetails.getClgname(),eduDetails.getUniversityname(),eduDetails.getCourse(),eduDetails.getAddress(),eduDetails.getId()};
        int[] types = {Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};

        return jdbcTemplate.update(query, params, types);
    }

    public Integer delete_eduDetailsById(Integer id){
        return jdbcTemplate.update("delete from edudetails_table where id = ?",id);
    }
}
