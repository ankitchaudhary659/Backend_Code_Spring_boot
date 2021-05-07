package com.example.demo.repository;


import com.example.demo.bean.JoiningDetails;
import com.example.demo.bean.JoiningDetailsRowMapper;
import com.example.demo.bean.trend2;
import com.example.demo.bean.trend2RowMapper;
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
public class JoiningDetailsRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<JoiningDetails> getJoiningDetails(){
        return  jdbcTemplate.query("select * from joiningdetails",new JoiningDetailsRowMapper());
    }

    public List<trend2> getCountPerLocation(){
        return  jdbcTemplate.query("select location,Count(id) from joiningdetails group by location",new trend2RowMapper());
    }

    public JoiningDetails findById(Integer id){

        String sql = "SELECT * FROM joiningdetails WHERE ID = ?";
        try{
            return  (JoiningDetails) this.jdbcTemplate.queryForObject(
                    sql, new Object[] { id }, new JoiningDetailsRowMapper());
        }
        catch(EmptyResultDataAccessException ex){
            return null;
        }

    }

    public Boolean saveJoiningDetails(JoiningDetails joiningDetails){
        String query="insert into joiningdetails values(?,?,?,?)";
        return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {

                ps.setInt(1,joiningDetails.getId());
                ps.setString(2,joiningDetails.getDate());
                ps.setString(3,joiningDetails.getTechnology());
                ps.setString(4,joiningDetails.getLocation());
             //   ps.setString(5,joiningDetails.getFeedback());



                return ps.execute();

            }
        });
    }

    public Integer updateJoiningDetails(JoiningDetails joiningDetails){
        String query="update joiningdetails set date = ? , technology = ? , location = ? where id = ?";
        Object[] params = {joiningDetails.getDate(),joiningDetails.getTechnology(),joiningDetails.getLocation(),joiningDetails.getId()};
        int[] types = {Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.INTEGER};

        return jdbcTemplate.update(query, params, types);
    }

    public Integer deleteJoiningDetailsById(Integer id){
        return jdbcTemplate.update("delete from joiningdetails where id = ?",id);
    }

}


