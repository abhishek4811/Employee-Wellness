package com.wipro.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ChallengeResponse {
    private String challengeName;
    private LocalDate startDate;
    private LocalDate endDate;

    // Getters, setters, and constructors
}