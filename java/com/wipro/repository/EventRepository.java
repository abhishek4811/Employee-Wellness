package com.wipro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{
	List<Event> findByEmployeeId(Long employeeId);
}
