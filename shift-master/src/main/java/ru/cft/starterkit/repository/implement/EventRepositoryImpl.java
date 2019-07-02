package ru.cft.starterkit.repository.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.cft.starterkit.entity.Event;
import ru.cft.starterkit.exception.ObjectNotFoundException;
import ru.cft.starterkit.repository.EventRepository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EventRepositoryImpl implements EventRepository {

    private static final Logger log = LoggerFactory.getLogger(EventRepositoryImpl.class);

    private final AtomicLong idCounter = new AtomicLong();

    @Override
   public final Collection<Event> getAll() {return storage.values();}


    private final Map<Long, Event> storage = new ConcurrentHashMap<>();

    @Override
    public Event add(Event event) {
        event.setId(idCounter.incrementAndGet());
        storage.put(event.getId(), event);

        log.info("Added event to storage: {}", event);
        return event;
    }

    @Override
    public Event get(Long id) throws ObjectNotFoundException {
        Event event = storage.get(id);

        if (event == null) {
            log.error("Failed to get event with id '{}' from storage", id);
            throw new ObjectNotFoundException(String.format("Event with id %s not found", id));
        }

        log.info("Returned event with id '{}' from storage: {}", id, event);
        return event;
    }

}
