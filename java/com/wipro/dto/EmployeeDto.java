package com.wipro.dto;

import java.time.LocalDate;

import com.wipro.model_enum.Role;


import lombok.Data;


@Data
public class EmployeeDto {
	
	private Long id;
	private String name;
    private String email;
    private LocalDate dateOfBirth; 
    private String department; 
    private Role role;
	
    
    
}
