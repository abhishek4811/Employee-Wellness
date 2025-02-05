package com.wipro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.entity.Event;
import com.wipro.exception.GlobalExceptionHandler;
import com.wipro.repository.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) throws GlobalExceptionHandler{
        return eventRepository.findById(id)
        		.orElseThrow(() -> new GlobalExceptionHandler("no such event"));
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event event) {
        Event existing = eventRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setEventName(event.getEventName());
            existing.setDescription(event.getDescription());
            existing.setEventDate(event.getEventDate());
            return eventRepository.save(existing);
        }
        return null;
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
    
    public List<Event> getUserEvents(Long id) {
        return eventRepository.findByEmployeeId(id);
    }
}
