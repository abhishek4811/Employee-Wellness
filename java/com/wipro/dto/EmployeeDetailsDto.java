package com.wipro.dto;

import java.time.LocalDate;
import java.util.List;

import com.wipro.model_enum.Role;

import lombok.Data;

@Data
public class EmployeeDetailsDto {
	private Long id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;
    private String department;
    private Role role;
    
    private List<ProgramResponse> enrolledPrograms;
    private List<ChallengeResponse> challenges;
    private List<EventResponse> events;
}

