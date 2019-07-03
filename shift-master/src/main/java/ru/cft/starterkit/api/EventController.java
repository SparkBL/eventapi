package ru.cft.starterkit.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.cft.starterkit.entity.Event;
import ru.cft.starterkit.exception.ObjectNotFoundException;
import ru.cft.starterkit.service.EventService;

import java.util.Collection;

@RestController
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/events/regEvent",
            consumes = "application/json",
            produces = "application/json"
    )
   public Event add(@RequestBody Event event
    ) {
        return eventService.add(event);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/events/{id}",
            produces = "application/json")
    public Event get(@PathVariable(name = "id") Long id) {
        try {
            return eventService.get(id);
        }
        catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
    @GetMapping ( path = "/events/all",produces = "application/json")
    public Collection <Event> getAll() throws ObjectNotFoundException {return eventService.getAll();}


    @PatchMapping ( path = "/events/removal/{id}")
    public Event Cancel(@PathVariable(name = "id") Long id)throws ObjectNotFoundException {
        return eventService.cancel(id);}


     @GetMapping ( path = "/events/comingsoon", produces = "application/json")
    public Collection<Event> getComingsoon(){return  eventService.getComingsoon();}

    @GetMapping (path = "events/date/{starts:[\\S\\s]+}", produces = "application/json")
    public  Collection<Event> getDay(@PathVariable String starts) throws  ObjectNotFoundException{
        return  eventService.getDay(starts);
    }

}
