package com.example.demo.controller;

import com.example.demo.bean.Candidate_details;
import com.example.demo.bean.trend1;
import com.example.demo.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    CandidateRepository candidateRepository;

//    @GetMapping("/test")
//    public String test()
//    {
//        return "testing";
//    }

    @GetMapping()
    public List<Candidate_details> getAllCandidates()
    {
        return candidateRepository.get_Candidate();
    }

    @GetMapping("/trends1")
    public List<trend1> getCountPerLocation()
    {
        return candidateRepository.getCountPerLocation();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCandidate(@PathVariable("id") Integer id) {
        Candidate_details candidateDetails = candidateRepository.find_ById(id);
        if (candidateDetails == null) {
            return new ResponseEntity<String>("No User found with this "+ id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Candidate_details>(candidateDetails, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> createCandidate(@RequestBody Candidate_details candidateDetails) throws SQLIntegrityConstraintViolationException {
        if (candidateRepository.find_ById(candidateDetails.getId()) != null) {
            return new ResponseEntity<String>("Duplicate Entry "+ candidateDetails.getId(), HttpStatus.IM_USED);
        }
        candidateRepository.save_Candidate(candidateDetails);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping
    public ResponseEntity<?> updateCandidate(@RequestBody Candidate_details candidateDetails) {
        if (candidateRepository.find_ById(candidateDetails.getId()) == null) {
            return new ResponseEntity<String>("Unable to update as  User id " + candidateDetails.getId() + " not found.",
                    HttpStatus.NOT_FOUND);
        }

        candidateRepository.update_Candidate(candidateDetails);
        return new ResponseEntity<Candidate_details>(candidateDetails, HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCandidate(@PathVariable("id") Integer id) {
        Candidate_details candidateDetails =candidateRepository.find_ById(id);
        if (candidateDetails == null) {
            return new ResponseEntity<String>("Unable to delete as  User id " + id + " not found.", HttpStatus.NOT_FOUND);
        }
        candidateRepository.delete_CandidateById(id);
        return new ResponseEntity<Candidate_details>(HttpStatus.NO_CONTENT);
    }
}
