package ru.cft.starterkit.repository;

import ru.cft.starterkit.entity.SampleEntity;
import ru.cft.starterkit.exception.ObjectNotFoundException;

import java.util.Collection;

public interface SampleEntityRepository {

    SampleEntity add(SampleEntity entity);

    SampleEntity get(Long id) throws ObjectNotFoundException;

    Collection<SampleEntity>getAll() throws ObjectNotFoundException;
}
