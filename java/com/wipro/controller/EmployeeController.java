package com.wipro.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.wipro.dto.EmployeeDetailsDto;
import com.wipro.entity.Challenge;
import com.wipro.entity.Employee;
import com.wipro.entity.Event;
import com.wipro.entity.WellnessProgram;
import com.wipro.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("admin/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;


	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		try {
			Employee employee = employeeService.getEmployeeById(id);
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);

		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		try {
			List<Employee> employeeList = employeeService.getAllEmployee();
			return ResponseEntity.ok(employeeList);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		try {
			Employee createdEmployee = employeeService.addEmployee(employee);
			return new ResponseEntity<>(createdEmployee,HttpStatus.CREATED);
		}catch(Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployees(@PathVariable Long id , @RequestBody Employee employee){
		try {
			Employee updatedEmployee = employeeService.updateEmployee(id, employee);
			return ResponseEntity.ok(updatedEmployee);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String,String>> deleteEmployee(@PathVariable Long id) {
		try {
			boolean isDeleted = employeeService.deleteEmployee(id); 
			if(isDeleted) {
				Map<String, String> response = new HashMap<>();
                response.put("message", "Successfully deleted");
			}
			return ResponseEntity.noContent().build();
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

	}

//	@GetMapping("/{id}/details")
//    public EmployeeDetailsDto getEmployeeDetails(@PathVariable Long id) {
//        return employeeService.getEmployeeDetails(id);
//    }
	
	@GetMapping("/{id}/challenges")
    public List<Challenge> getEmployeeChallenges(@PathVariable Long id) {
        return employeeService.getEmployeeChallenges(id);
    }

//    @GetMapping("/{id}/programs")
//    public List<WellnessProgram> getEmployeePrograms(@PathVariable Long id) {
//        return employeeService.getEmployeePrograms(id);
//    }

    @GetMapping("/{id}/events")
    public List<Event> getEmployeeEvents(@PathVariable Long id) {
        return employeeService.getEmployeeEvents(id);
    }



}




