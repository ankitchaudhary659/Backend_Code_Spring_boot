package com.example.demo.controller;


import com.example.demo.bean.JoiningDetails;
import com.example.demo.bean.trend2;
import com.example.demo.repository.JoiningDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/joinings")
public class JoiningDetailsController {

    @Autowired
    JoiningDetailsRepository joiningDetailsRepository;

    @GetMapping("/test")
    public String test()
    {
        return "testing";
    }

    @GetMapping()
    public List<JoiningDetails> getAllJoiningDetails()
    {
        return joiningDetailsRepository.getJoiningDetails();
    }

    @GetMapping("/trends2")
    public List<trend2> getCountPerLocation()
    {
        return joiningDetailsRepository.getCountPerLocation();
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getJoiningDetails(@PathVariable("id") Integer id) {
        JoiningDetails joiningDetails = joiningDetailsRepository.findById(id);
        if (joiningDetails == null) {
            return new ResponseEntity<String>("No Joining details found with this "+ id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<JoiningDetails>(joiningDetails, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> createJoiningDetails(@RequestBody JoiningDetails joiningDetails) throws SQLIntegrityConstraintViolationException {
        if (joiningDetailsRepository.findById(joiningDetails.getId()) != null) {
            return new ResponseEntity<String>("Duplicate Entry "+ joiningDetails.getId(), HttpStatus.IM_USED);
        }
        joiningDetailsRepository.saveJoiningDetails(joiningDetails);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping
    public ResponseEntity<?> updateJoiningDetails(@RequestBody JoiningDetails joiningDetails) {
        if (joiningDetailsRepository.findById(joiningDetails.getId()) == null) {
            return new ResponseEntity<String>("Unable to update as  Joining id " + joiningDetails.getId() + " not found.",
                    HttpStatus.NOT_FOUND);
        }

        joiningDetailsRepository.updateJoiningDetails(joiningDetails);
        return new ResponseEntity<JoiningDetails>(joiningDetails, HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteJoiningDetails(@PathVariable("id") Integer id) {
        JoiningDetails joiningDetails =joiningDetailsRepository.findById(id);
        if (joiningDetails == null) {
            return new ResponseEntity<String>("Unable to delete as  Joining Details id " + id + " not found.", HttpStatus.NOT_FOUND);
        }
        joiningDetailsRepository.deleteJoiningDetailsById(id);
        return new ResponseEntity<JoiningDetails>(HttpStatus.NO_CONTENT);
    }
   /* @GetMapping("/trends1")
    public List<Trends1> getCountPerYear()
    {
        return joiningDetailsRepository.getCountPerYear();
    }*/
}
