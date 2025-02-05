package com.wipro.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EventResponse {
	private Long id;
    private String eventName;
    private String description;
    private LocalDate eventDate;
    private Long employeeId;
    // Getters, setters, and constructors
}