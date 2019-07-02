package ru.cft.starterkit.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.starterkit.entity.SampleEntity;
import ru.cft.starterkit.exception.ObjectNotFoundException;
import ru.cft.starterkit.repository.SampleEntityRepository;
import ru.cft.starterkit.service.SampleEntityService;

import java.util.Collection;
import java.util.UUID;

@Service
public class SampleEntityServiceImpl implements SampleEntityService {

    private final SampleEntityRepository sampleEntityRepository;

    @Autowired
    public SampleEntityServiceImpl(SampleEntityRepository sampleEntityRepository) {
        this.sampleEntityRepository = sampleEntityRepository;
    }

    @Override
    public SampleEntity add(String foo, Double bar) {
        return sampleEntityRepository.add(new SampleEntity(foo, bar, UUID.randomUUID()));
    }

    @Override
    public SampleEntity get(Long id) throws ObjectNotFoundException {
        return sampleEntityRepository.get(id);


        }

    @Override
    public Collection<SampleEntity> getAll() throws ObjectNotFoundException {
        return sampleEntityRepository.getAll();
    }

    @Override
    public SampleEntity add(SampleEntity sampleEntity) {sampleEntity.setBaz(UUID.randomUUID());
    return sampleEntityRepository.add(sampleEntity);
    }


}


