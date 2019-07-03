package ru.cft.starterkit.repository;

import ru.cft.starterkit.entity.Event;
import ru.cft.starterkit.exception.ObjectNotFoundException;

import java.text.ParseException;
import java.util.Collection;

public interface EventRepository {

     Collection<Event> getDay(String starts)  throws ObjectNotFoundException;

    Collection<Event> getComingsoon() throws ParseException;

    Event add(Event entity);

    Event get(Long id) throws ObjectNotFoundException;

    Collection<Event>getAll() throws ObjectNotFoundException;

    Event cancel(Long id);
}
