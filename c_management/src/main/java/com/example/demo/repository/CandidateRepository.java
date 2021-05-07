package com.example.demo.repository;

import com.example.demo.bean.Candidate_details;
import com.example.demo.bean.CandidateRowMapper;
import com.example.demo.bean.trend1;
import com.example.demo.bean.trends1RowMapper;
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
public class CandidateRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //   @org.jetbrains.annotations.NotNull
    public  List<Candidate_details> get_Candidate(){
        return  jdbcTemplate.query("select * from candidate_table",new CandidateRowMapper());
    }


    public List<trend1> getCountPerLocation(){
        return  jdbcTemplate.query("select city,Count(id) from candidate_table group by city",new trends1RowMapper());
    }


    public Candidate_details find_ById(Integer id){

        String sql = "SELECT * FROM candidate_table WHERE ID = ?";
        try{
            return  (Candidate_details) jdbcTemplate.queryForObject(
                    sql, new Object[] { id }, new CandidateRowMapper());
        }
        catch(EmptyResultDataAccessException ex){
            return null;
        }

    }

    public  Boolean save_Candidate(Candidate_details candidateDetails){
        String query="insert into candidate_table values(?,?,?,?,?,?,?,?)";
        return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {

                ps.setInt(1, candidateDetails.getId());
                ps.setString(2, candidateDetails.getFirstname());
                ps.setString(3, candidateDetails.getLastname());
                ps.setString(4, candidateDetails.getPhoneno());
                ps.setString(5, candidateDetails.getEmail());
                ps.setString(6, candidateDetails.getBirthdate());
                ps.setString(7, candidateDetails.getCity());
                ps.setString(8, candidateDetails.getCountry());


                return ps.execute();

            }
        });
    }

    public  Integer update_Candidate(Candidate_details candidateDetails){
        String query="update candidate_table set firstname = ? , lastname = ? , phoneno = ? , email = ? , birthdate = ?,city = ? , country = ? where id = ?";
        Object[] params = {candidateDetails.getFirstname(), candidateDetails.getLastname(), candidateDetails.getPhoneno(), candidateDetails.getEmail(), candidateDetails.getBirthdate(), candidateDetails.getCity(), candidateDetails.getCountry(), candidateDetails.getId()};
        int[] types = {Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};

        return jdbcTemplate.update(query, params, types);
    }

    public  Integer delete_CandidateById(Integer id){
        return jdbcTemplate.update("delete from candidate_table where id = ?",id);
    }
}

