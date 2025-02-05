package com.wipro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.wipro.dto.LoginDto;
import com.wipro.dto.RequestResponse;
import com.wipro.service.EmployeeService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api")
public class AuthController {
	@Autowired
	private EmployeeService employeeService;

	
	@PostMapping("/login")
	public RequestResponse login(@RequestBody RequestResponse loginRequest){
		RequestResponse response = new RequestResponse();
		try {
			return employeeService.authenticate(loginRequest);
		}catch(Exception e) {
			response.setError(e.getMessage());
			response.setStatusCode(500);
			return response;
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<RequestResponse> register(@Valid @RequestBody RequestResponse reqres ){
		System.out.println("Recieved Employee Data: "+reqres.toString());
		RequestResponse response = new RequestResponse();
			return ResponseEntity.ok(employeeService.register(reqres));
		
	}
}
