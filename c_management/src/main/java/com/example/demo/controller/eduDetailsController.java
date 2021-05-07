package com.example.demo.controller;

import com.example.demo.bean.eduDetails;
import com.example.demo.repository.eduDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/eduDetails")
public class eduDetailsController {

    @Autowired
    eduDetailsRepository eduDetailsRepository;

     @GetMapping("/test")
    public String test() {
        return "testing";
    }

    @GetMapping()
    public List<eduDetails> getAll_eduDetails()
    {
        return eduDetailsRepository.get_eduDetails();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCandidates(@PathVariable("id") Integer id) {
        eduDetails edu = eduDetailsRepository.find_ById(id);
        if (edu == null) {
            return new ResponseEntity<String>("No eduDetails found with this "+ id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<eduDetails>( edu, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> createCandidates(@RequestBody eduDetails edu) throws SQLIntegrityConstraintViolationException {
        if (eduDetailsRepository.find_ById(edu.getId()) != null) {
            return new ResponseEntity<String>("Duplicate Entry "+ edu.getId(), HttpStatus.IM_USED);
        }
        eduDetailsRepository.save_eduDetails(edu);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping
    public ResponseEntity<?> updateCandidate(@RequestBody eduDetails edu) {
        if (eduDetailsRepository.find_ById(edu.getId()) == null) {
            return new ResponseEntity<String>("Unable to update as  User id " + edu.getId() + " not found.",
                    HttpStatus.NOT_FOUND);
        }

        eduDetailsRepository.update_eduDetails(edu);
        return new ResponseEntity<eduDetails>(edu, HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCandidates(@PathVariable("id") Integer id) {
        eduDetails edu =eduDetailsRepository.find_ById(id);
        if (edu == null) {
            return new ResponseEntity<String>("Unable to delete as  User id " + id + " not found.", HttpStatus.NOT_FOUND);
        }
        eduDetailsRepository.delete_eduDetailsById(id);
        return new ResponseEntity<eduDetails>(HttpStatus.NO_CONTENT);
    }
}
