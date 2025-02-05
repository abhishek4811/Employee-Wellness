package com.wipro.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.dto.WellnessProgramDto;
import com.wipro.entity.WellnessEnrollment;
import com.wipro.entity.WellnessProgram;
import com.wipro.exception.GlobalExceptionHandler;
import com.wipro.repository.WellnessEnrollmentRepository;
import com.wipro.repository.WellnessProgramRepository;

@Service
public class WellnessProgramService {
	
	@Autowired
	private WellnessProgramRepository wellnessProgramRepository;
	
	@Autowired
	private WellnessEnrollmentRepository wellnessEnrollmentRepository;
	
	 private WellnessProgramDto convertToDto(WellnessProgram wellnessProgram) {
	        WellnessProgramDto dto = new WellnessProgramDto();
	        dto.setId(wellnessProgram.getId());
	        dto.setProgramName(wellnessProgram.getProgramName());
	        dto.setDescription(wellnessProgram.getDescription());
	        dto.setStartDate(wellnessProgram.getStartDate());
	        dto.setEndDate(wellnessProgram.getEndDate());
	        return dto;
	    }
	
	
	
	public List<WellnessProgramDto> getAllPrograms() {
		return wellnessProgramRepository.findAll().stream()
	            .map(this::convertToDto)
	            .collect(Collectors.toList());
	}
	
	public WellnessProgram getProgramById(Long id) throws GlobalExceptionHandler{
		return wellnessProgramRepository.findById(id)
				.orElseThrow(() -> new GlobalExceptionHandler("employee not found"));
	}
	
	public WellnessProgram addProgram(WellnessProgram program) {
		return wellnessProgramRepository.save(program);
	}
	
	public WellnessProgram updateProgram(Long id , WellnessProgram program) {
		Optional<WellnessProgram> existingProgram = wellnessProgramRepository.findById(id);
		if(existingProgram.isPresent()) {
			WellnessProgram updatedProgram = existingProgram.get();
			updatedProgram.setProgramName(program.getProgramName());
			updatedProgram.setDescription(program.getDescription());
			updatedProgram.setStartDate(program.getStartDate());
			updatedProgram.setEndDate(program.getEndDate());
			
			return wellnessProgramRepository.save(updatedProgram);
		}
		return null;
	}
	
	public boolean deleteProgram(long id) {
		wellnessProgramRepository.deleteById(id);
		return true;
		
	}
	
	public List<WellnessProgram> getUserEnrolledPrograms(Long employeeId) {
        List<WellnessEnrollment> enrollment = wellnessEnrollmentRepository.findByEmployeeId(employeeId);
        return enrollment.stream()
        				 .map(WellnessEnrollment::getWellnessProgram)
        				 .collect(Collectors.toList());
    }
}
