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

import com.wipro.entity.Challenge;
import com.wipro.service.ChallengeService;

@RestController
@RequestMapping
public class ChallengeController {

	@Autowired
	private ChallengeService challengeService;

	@GetMapping("/challenges/all")
	public ResponseEntity<List<Challenge>> getAllChallenges() {
		try {
			return ResponseEntity.ok(challengeService.getAllChallenges());
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<Challenge> getChallengeById(@PathVariable Long id) {
		try {
			Challenge challenge = challengeService.getChallengeById(id);
			if (challenge != null) {
				return ResponseEntity.ok(challenge);
			}
			return ResponseEntity.notFound().build();
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("admin/challenges/add")
	public ResponseEntity<Challenge> createChallenge(@RequestBody Challenge challenge) {
		try {
			Challenge createdChallenge = challengeService.createChallenge(challenge);
			return ResponseEntity.ok(createdChallenge);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}


	}

	@PutMapping("/{id}")
	public ResponseEntity<Challenge> updateChallenge(@PathVariable Long id,  @RequestBody Challenge challenge) {
		try {
			Challenge updatedChallenge = challengeService.updateChallenge(id, challenge);
			if (updatedChallenge != null) {
				return ResponseEntity.ok(updatedChallenge);
			}
			return ResponseEntity.notFound().build();
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}


	}

	@DeleteMapping("admin/challenges/{id}")
	public ResponseEntity<Map<String,String>> deleteChallenge(@PathVariable Long id) {
		try {
			boolean isDeleted = challengeService.deleteChallenge(id);
			if(isDeleted) {
				Map<String, String> response = new HashMap<>();
                response.put("message", "Successfully deleted");
			}
			return ResponseEntity.noContent().build();
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}


	}

	@GetMapping("/user/{id}")
	public ResponseEntity<List<Challenge>> getUserChallenges(@PathVariable Long id){
		try {
			List<Challenge> challenge = challengeService.getUserChallenges(id);
			return ResponseEntity.ok(challenge);

		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
}
