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
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String eventName;
	private String description;
	private LocalDate eventDate;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

	public Event() {
		
	}
	
	public Event(Long id, String eventName, String description, String eventDate) {
		super();
		this.id = id;
		this.eventName = eventName;
		this.description = description;
		this.eventDate = LocalDate.parse(eventDate);
	}
	
	
}
