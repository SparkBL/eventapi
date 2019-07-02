package ru.cft.starterkit.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.cft.starterkit.entity.SampleEntity;
import ru.cft.starterkit.exception.ObjectNotFoundException;
import ru.cft.starterkit.service.SampleEntityService;

import java.util.Collection;

@RestController
public class SampleEntityController {

    private final SampleEntityService sampleEntityService;

    @Autowired
    public SampleEntityController(SampleEntityService sampleEntityService) {
        this.sampleEntityService = sampleEntityService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/sample",
            consumes = "application/json",
            produces = "application/json"
    )
   public SampleEntity add(@RequestBody SampleEntity sampleEntity
    ) {
        return sampleEntityService.add(sampleEntity);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/sample/{id}",
            produces = "application/json")
    public SampleEntity get(@PathVariable(name = "id") Long id) {
        try {
            return sampleEntityService.get(id);
        }
        catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
    @GetMapping ( path = "/sample/all",produces = "application/json")
    public Collection <SampleEntity> getAll() throws ObjectNotFoundException {return sampleEntityService.getAll();}
    @RequestMapping ( method = RequestMethod.DELETE, path = "/delete")
    public String delete(){return "Delete here";}

}
