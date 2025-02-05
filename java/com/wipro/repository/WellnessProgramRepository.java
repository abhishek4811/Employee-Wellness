package com.wipro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.entity.WellnessProgram;

@Repository
public interface WellnessProgramRepository extends JpaRepository<WellnessProgram, Long>{
//	 List<WellnessProgram> findByEmployeeId(Long employeeId);
}
