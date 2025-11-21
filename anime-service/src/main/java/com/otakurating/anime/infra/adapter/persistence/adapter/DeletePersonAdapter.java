package com.otakurating.anime.infra.adapter.persistence.adapter;

import com.otakurating.anime.core.port.out.DeletePersonPort;
import com.otakurating.anime.infra.adapter.persistence.repository.PersonMongoRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeletePersonAdapter implements DeletePersonPort {
    private final PersonMongoRepository personMongoRepository;

    public DeletePersonAdapter(PersonMongoRepository personMongoRepository) {
        this.personMongoRepository = personMongoRepository;
    }

    @Override
    public void deleteById(UUID id) {
        personMongoRepository.deleteById(id);
    }
}
