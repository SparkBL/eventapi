package ru.cft.starterkit.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.starterkit.entity.Event;
import ru.cft.starterkit.exception.ObjectNotFoundException;
import ru.cft.starterkit.repository.EventRepository;
import ru.cft.starterkit.service.EventService;

import java.util.Collection;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event add(String email, String name, String lastname, String starts, String ends) {
        return eventRepository.add(new Event(email, name, lastname, starts, ends, UUID.randomUUID()));
    }

    @Override
    public Event get(Long id) throws ObjectNotFoundException {
        return eventRepository.get(id);


        }

    @Override
    public Collection<Event> getAll() throws ObjectNotFoundException {
        return eventRepository.getAll();
    }

    @Override
    public Event add(Event event) {
        event.setBaz(UUID.randomUUID());
    return eventRepository.add(event);
    }


}


