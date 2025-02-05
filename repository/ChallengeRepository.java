package com.wipro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.entity.Challenge;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long>{
	List<Challenge> findByEmployeeId(Long id);
}
