package ru.cft.starterkit.repository.implement;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import ru.cft.starterkit.entity.Event;
import ru.cft.starterkit.exception.CrossongEventException;
import ru.cft.starterkit.exception.ObjectNotFoundException;
import ru.cft.starterkit.repository.EventRepository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@CrossOrigin
@Repository
public class EventRepositoryImpl implements EventRepository {

    private static final File STORAGE_FILE = new File("C:\\Users\\AL\\eventapi\\data.json");

    private static final Logger log = LoggerFactory.getLogger(EventRepositoryImpl.class);

    private final AtomicLong idCounter = new AtomicLong();

    private final ObjectMapper objectMapper;

    @Autowired
    public EventRepositoryImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
   public final Collection<Event> getAll() {
        ArrayList<Event> Sorted = new ArrayList<>();
        for (Event event : storage.values()) {
            if(event!=null&&!event.getCanceled())
            {Sorted.add(event);}
        }
        Collections.sort(Sorted);

        return Sorted;}
      //  return storage.values();}

    @Override
    public Event cancel(Long id) {
        Event newEvent = storage.get(id);
        newEvent.Cancel();
       storage.replace(id,newEvent);
        return newEvent;
    }

   // private final Map<Long, Event> soonStorage = new ConcurrentHashMap<>();

    private final Map<Long, Event> storage = new ConcurrentHashMap<>();

    @Override
    public Collection<Event> getDay(String starts)   {
        ArrayList<Event> Sorted = new ArrayList<>();
        for (Event event : storage.values()) {
            if(event!=null&&event.checkDay(starts))//{soonStorage.put(event.getId(),event);}
            {Sorted.add(event);}
        }
        Collections.sort(Sorted);

        return Sorted;
    }


    @Override
    public Collection<Event>  getComingsoon() throws ParseException {
        ArrayList<Event> Sorted = new ArrayList<>();
        for (Event event : storage.values()) {
            if(event!=null&&event.checkIfSoon())//{soonStorage.put(event.getId(),event);}
            {Sorted.add(event);}
            }
        Collections.sort(Sorted);
        return Sorted;
            }

                 
                 
                 
    @Override
    public Event add(Event event) throws CrossongEventException {
                    for (Event e : storage.values()) {
                        if ( event != null&&event.checkCrossing(e)&&!event.checkstarttoend())
                        {
                           log.error("Found crossing with other event with id {}", e.getId());
                           throw new CrossongEventException(String.format("Found crossing with %s", e));
                        }
                    }
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
    @PostConstruct
    private void initStorage() {
        try {
            Event[] entriesFromFile = objectMapper.readValue(STORAGE_FILE, Event[].class);
            for (Event event : entriesFromFile) {
                storage.put(event.getId(), event);
                if (idCounter.get() < event.getId()) {
                    idCounter.set(event.getId());
                }
            }
            log.info("Loaded {} entities to storage. Id counter set to {}.", storage.size(), idCounter.get());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    private void shutdown() {
        log.info("Start shutdown!");
        try {
            objectMapper.writeValue(STORAGE_FILE, storage.values());
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Shutdown is ready!");
    }
}
