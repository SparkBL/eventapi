package ru.cft.starterkit.service;

import ru.cft.starterkit.entity.Event;
import ru.cft.starterkit.exception.CrossongEventException;
import ru.cft.starterkit.exception.ObjectNotFoundException;

import java.util.Collection;

public interface EventService {

    Event add(String type, String email, String name, String lastname,String phone, String starts, String ends) throws CrossongEventException;

    Event get(Long id) throws ObjectNotFoundException;

    Collection<Event> getAll() throws  ObjectNotFoundException;

    Event add(Event event) throws CrossongEventException;

    Event cancel(Long id);

    Collection<Event> getComingsoon();

    Collection<Event> getDay(String starts) throws  ObjectNotFoundException;
}
