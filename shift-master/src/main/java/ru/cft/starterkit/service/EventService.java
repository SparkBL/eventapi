package ru.cft.starterkit.service;

import ru.cft.starterkit.entity.Event;
import ru.cft.starterkit.exception.ObjectNotFoundException;

import java.util.Collection;

public interface EventService {

    Event add(String email, String name, String lastname, String starts, String ends);

    Event get(Long id) throws ObjectNotFoundException;

    Collection<Event> getAll() throws  ObjectNotFoundException;

    Event add(Event event);
}
