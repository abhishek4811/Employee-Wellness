package com.wipro.controller;

import java.util.List;


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

import com.wipro.entity.Event;
import com.wipro.service.EventService;

@RestController
@RequestMapping
public class EventController {

	@Autowired
	private EventService eventService;

	@GetMapping("events/all")
	public ResponseEntity<List<Event>> getAllEvents() {
		try {
			return ResponseEntity.ok(eventService.getAllEvents());
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("admin/events/{id}")
	public ResponseEntity<Event> getEventById(@PathVariable Long id) {
		try {
			Event event = eventService.getEventById(id);
			if (event != null) {
				return ResponseEntity.ok(event);
			}
			return ResponseEntity.notFound().build();
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}


	}

	@PostMapping("admin/events/add")
	public ResponseEntity<Event> createEvent(@RequestBody Event event) {
		try {
			Event createdEvent = eventService.createEvent(event);
			return ResponseEntity.ok(createdEvent);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}


	}

	@PutMapping("admin/events/{id}")
	public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
		try {
			Event updatedEvent = eventService.updateEvent(id, event);
			if (updatedEvent != null) {
				return ResponseEntity.ok(updatedEvent);
			}
			return ResponseEntity.notFound().build();
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}


	}

	@DeleteMapping("admin/events/{id}")
	public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
		try {
			eventService.deleteEvent(id);
			return ResponseEntity.noContent().build();
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}


	}
}
