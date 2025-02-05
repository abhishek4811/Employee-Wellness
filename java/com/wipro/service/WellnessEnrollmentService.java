package com.wipro.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.entity.Employee;
import com.wipro.entity.WellnessEnrollment;
import com.wipro.entity.WellnessProgram;
import com.wipro.exception.GlobalExceptionHandler;
import com.wipro.repository.EmployeeRepository;
import com.wipro.repository.WellnessEnrollmentRepository;
import com.wipro.repository.WellnessProgramRepository;

@Service
public class WellnessEnrollmentService {

	@Autowired
	private WellnessEnrollmentRepository wellnessEnrollmentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private WellnessProgramRepository wellnessProgramRepository;


	public List<WellnessEnrollment> getAllEnrollments() {
		return wellnessEnrollmentRepository.findAll();
	}

	public WellnessEnrollment getEnrollmentById(Long id) throws GlobalExceptionHandler{
		return wellnessEnrollmentRepository.findById(id)
				.orElseThrow(() -> new GlobalExceptionHandler("not found"));
	}

	public WellnessEnrollment createEnrollment(WellnessEnrollment wellnessEnrollment) {
		return wellnessEnrollmentRepository.save(wellnessEnrollment);
	}

	public WellnessEnrollment updateEnrollment(Long id, WellnessEnrollment wellnessEnrollment) {
		Optional<WellnessEnrollment> existingEnroll = wellnessEnrollmentRepository.findById(id);
		if (existingEnroll.isPresent()) {
			WellnessEnrollment updatedEnroll = existingEnroll.get();
			updatedEnroll.setEmployee(wellnessEnrollment.getEmployee());
			updatedEnroll.setWellnessProgram(wellnessEnrollment.getWellnessProgram());
			updatedEnroll.setEnrollmentDate(wellnessEnrollment.getEnrollmentDate());
			return wellnessEnrollmentRepository.save(updatedEnroll);
		}
		return null;
	}

	public void deleteEnrollment(Long id) {
		wellnessEnrollmentRepository.deleteById(id);

	}

	public void enrollInProgram(Long employeeId , Long programId) {
		try {
			Employee employee = employeeRepository.findById(employeeId)
					.orElseThrow(() -> new Exception("Employee not found"));
			
			WellnessProgram program = wellnessProgramRepository.findById(programId)
					.orElseThrow(() -> new Exception("Program not found"));
			
			WellnessEnrollment enrollment = new WellnessEnrollment();
			enrollment.setEmployee(employee);
			enrollment.setWellnessProgram(program);
			enrollment.setEnrollmentDate(LocalDate.now());

			wellnessEnrollmentRepository.save(enrollment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

