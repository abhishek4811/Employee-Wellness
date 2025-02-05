package com.wipro.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.wipro.dto.WellnessProgramDto;
import com.wipro.entity.WellnessProgram;
import com.wipro.service.WellnessProgramService;

@RestController
@RequestMapping("/program")
public class WellnessProgramController {

    @Autowired
    private WellnessProgramService wellnessProgramService;

    @GetMapping("/all")
    public ResponseEntity<List<WellnessProgramDto>> getAllWellnessPrograms() {
        try {
        	List<WellnessProgramDto> programs = wellnessProgramService.getAllPrograms();
        	return ResponseEntity.ok(programs);
        }catch(Exception e) {
        	throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    	
    }

    @GetMapping("/{id}")
    public ResponseEntity<WellnessProgram> getWellnessProgramById(@PathVariable Long id) {
        try {
        	WellnessProgram wellnessProgram = wellnessProgramService.getProgramById(id);
        	return new ResponseEntity<WellnessProgram>(wellnessProgram,HttpStatus.OK);
        }catch(Exception e) {
        	throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        
    }

    @PostMapping("/add")
    public ResponseEntity<WellnessProgram> addWellnessProgram( @RequestBody WellnessProgram wellnessProgram) {
        try{
        	WellnessProgram createdProgram = wellnessProgramService.addProgram(wellnessProgram);
        	 return ResponseEntity.ok(createdProgram);
        }catch(Exception e) {
        	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<WellnessProgram> updateWellnessProgram(@PathVariable Long id,  @RequestBody WellnessProgram wellnessProgram) {
        try {
        	WellnessProgram updatedProgram = wellnessProgramService.updateProgram(id, wellnessProgram);
            if (updatedProgram != null) {
                return ResponseEntity.ok(updatedProgram);
            }
            return ResponseEntity.notFound().build();
        }catch(Exception e) {
        	throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    	
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>>deleteWellnessProgram(@PathVariable Long id) {
        try {
        	boolean isDeleted = wellnessProgramService.deleteProgram(id);

            if (isDeleted) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Successfully deleted");

                // Return the map wrapped in ResponseEntity
                return ResponseEntity.ok(response);  // Status 200 with JSON message
            } else {
                // Return an error message if the deletion failed
                Map<String, String> response = new HashMap<>();
                response.put("error", "Deletion failed");

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);  // Status 500
            }
            
        }catch(Exception e) {
        	throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    	
    }
    
    @GetMapping("/user/{id}")
    public ResponseEntity<List<WellnessProgram>> getUserEnrolledPrograms(@PathVariable Long employeeId) {
        try {
        	List<WellnessProgram> programs = wellnessProgramService.getUserEnrolledPrograms(employeeId);
            return new ResponseEntity<>(programs, HttpStatus.OK);
        }catch(Exception e) {
        	throw new ResponseStatusException( HttpStatus.BAD_REQUEST);
        }
    	
    }
}
