package com.wipro.entity;


import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wipro.model_enum.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Data
@Entity
public class Employee implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Name cannot be null")
	private String name;
	
	@Email(message = "Email should be valid")
	private String email;
	
	
	private String password;
	
	@NotNull(message = "Date of birth cannot be null")
	private LocalDate dob;
	
	@NotNull(message = "department cannot be null")
	private String department;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@OneToMany(mappedBy = "employee")
	private List<Challenge> challenges;
	
	@OneToMany(mappedBy = "employee")
	@JsonIgnore
	private List<WellnessEnrollment> enrollments;
	
	@OneToMany(mappedBy = "employee")
	private List<Event> events;
	
	public Employee() {
		
	}

	public Employee(Long id, String name, String email, String dob, String department, Role role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.dob = LocalDate.parse(dob);
		this.department = department;
		this.role = role;
		
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(this.getRole().name()));
	}


	@Override
	public String getUsername() {
		return email;
	}

	


	
	
	
}
