package ru.cft.starterkit.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.starterkit.entity.Event;
import ru.cft.starterkit.exception.CrossongEventException;
import ru.cft.starterkit.exception.ObjectNotFoundException;
import ru.cft.starterkit.repository.EventRepository;
import ru.cft.starterkit.service.EventService;

import java.text.ParseException;
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
    public Event add(String type,String email, String name, String lastname, String phone, String starts, String ends) throws CrossongEventException {
        return eventRepository.add(new Event(type, email, name, lastname, phone, starts, ends, UUID.randomUUID()));
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
    public Event add(Event event) throws CrossongEventException {
        event.setBaz(UUID.randomUUID());
    return eventRepository.add(event);
    }

    @Override
    public Event cancel(Long id) {
        return eventRepository.cancel(id);
    }

    @Override
    public Collection<Event> getComingsoon() {
        try {
            return eventRepository.getComingsoon();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Event> getDay(String starts) {
        try {
            return eventRepository.getDay(starts);
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        return  null;
    }


}


