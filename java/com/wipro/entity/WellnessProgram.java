package com.wipro.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString

@Entity
public class WellnessProgram {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String programName;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	
	@OneToMany(mappedBy = "wellnessProgram")
	private List<WellnessEnrollment> enrollments;
	
	
	public WellnessProgram() {
		
	}


	public WellnessProgram(Long id, String programName, String description, String startDate, String endDate) {
		super();
		Id = id;
		this.programName = programName;
		this.description = description;
		this.startDate = LocalDate.parse(startDate);
		this.endDate = LocalDate.parse(endDate);
	}
	
	

}
