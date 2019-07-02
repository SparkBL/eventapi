package ru.cft.starterkit.repository;

import ru.cft.starterkit.entity.Event;
import ru.cft.starterkit.exception.ObjectNotFoundException;

import java.util.Collection;

public interface EventRepository {

    Event add(Event entity);

    Event get(Long id) throws ObjectNotFoundException;

    Collection<Event>getAll() throws ObjectNotFoundException;
}
