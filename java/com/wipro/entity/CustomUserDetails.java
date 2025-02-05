//package com.wipro.entity;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.wipro.model_enum.Role;
//
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotNull;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//
//@Data
//public class CustomUserDetails implements UserDetails{
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	private String name;
//	private String email;
//	
//	
//	private String password;
//	private LocalDate dob;
//	
//	private String department;
//	
//	@Enumerated(EnumType.STRING)
//	private Role role;
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
////		List<GrantedAuthority> authorities = new ArrayList<>();
////		authorities.add(new SimpleGrantedAuthority(employee.getRole().name()));
////		return authorities;
//		
//		return List.of(new SimpleGrantedAuthority(this.getRole().name()));
//	}
//
//
//	@Override
//	public String getUsername() {
//		return email;
//	}
//	
//}
