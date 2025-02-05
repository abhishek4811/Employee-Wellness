package com.wipro.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class WellnessEnrollment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "program_id")
	private WellnessProgram wellnessProgram;
	
	private LocalDate enrollmentDate;

	public WellnessEnrollment() {
		
	}
	
	public WellnessEnrollment(Long id, Employee employee,WellnessProgram wellnessProgram, String enrollmentDate) {
		super();
		this.id = id;
		this.employee = employee;
		this.wellnessProgram = wellnessProgram;
		this.enrollmentDate = LocalDate.parse(enrollmentDate);
	}
	
	
}
