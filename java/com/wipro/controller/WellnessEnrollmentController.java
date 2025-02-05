package com.wipro.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.wipro.dto.EnrollmentRequest;
import com.wipro.entity.WellnessEnrollment;
import com.wipro.service.WellnessEnrollmentService;


@RestController
@RequestMapping("/enrollment")
public class WellnessEnrollmentController {

	@Autowired
	private WellnessEnrollmentService wellnessEnrollmentService;
	
	
	
	

	@GetMapping
	public ResponseEntity<List<WellnessEnrollment>> getAllEnrollments() {
		try {
			return ResponseEntity.ok(wellnessEnrollmentService.getAllEnrollments());

		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<WellnessEnrollment> getEnrollmentById(@PathVariable Long id) {
		try {
			WellnessEnrollment enrollment = wellnessEnrollmentService.getEnrollmentById(id);
			return new ResponseEntity<WellnessEnrollment>(enrollment,HttpStatus.OK);
			//            return ResponseEntity.notFound().build();
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping
	public ResponseEntity<WellnessEnrollment> createEnrollment(@RequestBody WellnessEnrollment enrollment) {
		try {
			WellnessEnrollment createdEnrollment = wellnessEnrollmentService.createEnrollment(enrollment);
			return ResponseEntity.ok(createdEnrollment);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<WellnessEnrollment> updateEnrollment(@PathVariable Long id, @RequestBody WellnessEnrollment enrollment) {
		try {
			WellnessEnrollment updatedEnrollment = wellnessEnrollmentService.updateEnrollment(id, enrollment);
			if (updatedEnrollment != null) {
				return ResponseEntity.ok(updatedEnrollment);
			}
			return ResponseEntity.notFound().build();
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}


	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEnrollment(@PathVariable Long id) {
		try {
			wellnessEnrollmentService.deleteEnrollment(id);
			return ResponseEntity.ok("Successfully deleted");
			//    		return ResponseEntity.noContent().build();
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}


	}
	
//	POST (/enrollment/enroll?employeeId=1&programId=2))
	@PostMapping("/enroll")
	@CrossOrigin(origins = "http://localhost:4200.wellness-programs") 
	public ResponseEntity<Map<String,String>> enrollInProgram(@RequestBody EnrollmentRequest request) {
		try {
			Map<String ,String> response = new HashMap<>();
			response.put("message", "enrolled successfully");
			wellnessEnrollmentService.enrollInProgram(request.getEmployeeId() , request.getProgramId());
			return ResponseEntity.ok(response);
		}catch(Exception e) {
			throw new  ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
}






