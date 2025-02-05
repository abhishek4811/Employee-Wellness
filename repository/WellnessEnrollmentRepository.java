package com.wipro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.entity.WellnessEnrollment;

@Repository
public interface WellnessEnrollmentRepository extends JpaRepository<WellnessEnrollment, Long>{
	List<WellnessEnrollment> findByEmployeeId(Long employeeId);
}
