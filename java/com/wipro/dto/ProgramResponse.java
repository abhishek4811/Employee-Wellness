package com.wipro.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProgramResponse {
	private Long id;
    private String programName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    // Getters, setters, and constructors
}