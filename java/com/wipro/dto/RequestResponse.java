package com.wipro.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.wipro.entity.Employee;
import com.wipro.model_enum.Role;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties (ignoreUnknown = true)
public class RequestResponse {
	private int statusCode;
	private Long employeeId;
	private String error;
	private String message;
	private String token;
	private String refreshToken;
	private String expirationTime;
	private String name;
	private Role role;
	private String email;
	private String password;
	private String Department;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	private Employee employee;
	private List<Employee> employeeList;
	
	public RequestResponse() {
		
	}
	public RequestResponse(String name, Role role, String email, String password, String department, String dateOfBirth) {
		super();
		this.name = name;
		this.role = role;
		this.email = email;
		this.password = password;
		this.Department = department;
		this.dateOfBirth = LocalDate.parse(dateOfBirth);
	}
	
	
}
