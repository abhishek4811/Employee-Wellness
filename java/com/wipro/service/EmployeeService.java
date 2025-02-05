package com.wipro.service;


import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wipro.dto.EmployeeDetailsDto;
import com.wipro.dto.EventResponse;
import com.wipro.dto.ProgramResponse;
import com.wipro.dto.RequestResponse;
import com.wipro.entity.Challenge;
import com.wipro.entity.Employee;
import com.wipro.entity.Event;
import com.wipro.entity.WellnessEnrollment;
import com.wipro.entity.WellnessProgram;
import com.wipro.exception.GlobalExceptionHandler;
import com.wipro.repository.ChallengeRepository;
import com.wipro.repository.EmployeeRepository;
import com.wipro.repository.EventRepository;
import com.wipro.repository.WellnessEnrollmentRepository;
import com.wipro.security.JwtUtilities;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
    private WellnessEnrollmentRepository wellnessEnrollmentRepository;

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ChallengeRepository challengeRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtilities jwtUtilities;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}
	
	public Employee getEmployeeById(Long id) throws GlobalExceptionHandler {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new GlobalExceptionHandler("employee not found"));
	}
	
	public Employee updateEmployee(Long id , Employee employee) throws GlobalExceptionHandler{
		Optional<Employee> existingEmployee = employeeRepository.findById(id);
		if(existingEmployee.isPresent()) {
			Employee updatedEmployee = existingEmployee.get();
			updatedEmployee.setName(employee.getName());
			updatedEmployee.setEmail(employee.getEmail());
			updatedEmployee.setDob(employee.getDob());
			updatedEmployee.setDepartment(employee.getDepartment());
			updatedEmployee.setRole(employee.getRole());
			
			return employeeRepository.save(updatedEmployee);
			
		}
		return null;
	}
	
	public boolean deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
		return true;
	}
	
	//NOT WORKING
	public EmployeeDetailsDto getEmployeeDetails(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));

        EmployeeDetailsDto employeeDetails = new EmployeeDetailsDto();
        
        EmployeeDetailsDto employeeDTO = new EmployeeDetailsDto();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setDateOfBirth(employee.getDob());
        employeeDTO.setDepartment(employee.getDepartment());
        employeeDTO.setRole(employee.getRole());
        
     // Fetch enrollments
       List<WellnessEnrollment> enrollments = wellnessEnrollmentRepository.findByEmployeeId(employeeId);
        List<ProgramResponse> programDTOs = enrollments.stream().map(e -> {
            WellnessProgram program = e.getWellnessProgram();
            ProgramResponse dto = new ProgramResponse();
            dto.setId(program.getId());
            dto.setProgramName(program.getProgramName());
            dto.setDescription(program.getDescription());
            dto.setStartDate(program.getStartDate());
            dto.setEndDate(program.getEndDate());
            System.out.println(dto);
            return dto;
        }).collect(Collectors.toList());
        employeeDTO.setEnrolledPrograms(programDTOs);
        
     // Fetch events
        List<Event> events = eventRepository.findByEmployeeId(employeeId);
        List<EventResponse> eventDTOs = events.stream().map(e -> {
            EventResponse dto = new EventResponse();
            dto.setId(e.getId());
            dto.setEventName(e.getEventName());
            dto.setDescription(e.getDescription());
            dto.setEventDate(e.getEventDate());
            dto.setEmployeeId(e.getEmployee().getId());
            System.out.println(dto);
            return dto;
        }).collect(Collectors.toList());

        // Set the DTOs to employeeDTO
        employeeDetails.setEnrolledPrograms(programDTOs);
        employeeDetails.setEvents(eventDTOs);

        return employeeDetails;
        
	}
	

	public RequestResponse register(RequestResponse reqres) {
		RequestResponse registerResponse = new RequestResponse();
		try {
			if(employeeRepository.existsByEmail(reqres.getEmail())){
				registerResponse.setMessage("email already exists");
			}
			else {
				Employee employee = new Employee();
				employee.setName(reqres.getName());
				employee.setEmail(reqres.getEmail());
//				employee.setPassword(employeedto.getPassword());
				employee.setPassword(passwordEncoder.encode(reqres.getPassword()));
				employee.setDob(reqres.getDateOfBirth()); // Ensure this is set
				employee.setDepartment(reqres.getDepartment());
				employee.setRole(reqres.getRole());
				employeeRepository.save(employee);
				registerResponse.setMessage("successfuly registered");
				registerResponse.setStatusCode(200);
			}
		}catch(Exception e) {
			registerResponse.setError(e.getMessage());
			registerResponse.setStatusCode(500);
		}
		
		return registerResponse;
	}

	public RequestResponse authenticate(RequestResponse loginRequest) {
		RequestResponse loginResponse = new RequestResponse();
		authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                loginRequest.getPassword()));
		var employee = employeeRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
        String token = jwtUtilities.generateToken(employee);
        String refreshToken = jwtUtilities.generateRefreshToken(new HashMap<>() ,employee);
        
        loginResponse.setStatusCode(200);
        loginResponse.setToken(token);
        loginResponse.setRefreshToken(refreshToken);
        loginResponse.setEmployeeId(employee.getId());
        loginResponse.setRole(employee.getRole());
        loginResponse.setMessage("logged in");
        return loginResponse;
	}
	
	
	

    public List<Challenge> getEmployeeChallenges(Long employeeId) {
        return challengeRepository.findByEmployeeId(employeeId);
    }

//    public List<WellnessProgram> getEmployeePrograms(Long employeeId) {
//        return WellnessEnrollmentRepository.findByEmployeeId(employeeId);
//    }

    public List<Event> getEmployeeEvents(Long employeeId) {
        return eventRepository.findByEmployeeId(employeeId);
    }
	
	
	
	
	
	
	
	
	
}
