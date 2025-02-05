package com.wipro.entity;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString

@Entity
public class Challenge {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String challengeName;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	public Challenge() {
		
	}

	public Challenge(Long id, String challengeName, String description, String startDate, String endDate) {
		super();
		this.id = id;
		this.challengeName = challengeName;
		this.description = description;
		this.startDate = LocalDate.parse(endDate);
		this.endDate = LocalDate.parse(endDate);
	}
	
	
}
