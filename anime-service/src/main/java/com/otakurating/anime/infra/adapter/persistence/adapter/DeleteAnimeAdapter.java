package com.otakurating.anime.infra.adapter.persistence.adapter;

import com.otakurating.anime.core.port.out.DeleteAnimePort;
import com.otakurating.anime.infra.adapter.persistence.repository.AnimeMongoRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteAnimeAdapter implements DeleteAnimePort {
    private final AnimeMongoRepository animeMongoRepository;

    public DeleteAnimeAdapter(AnimeMongoRepository animeMongoRepository) {
        this.animeMongoRepository = animeMongoRepository;
    }

    @Override
    public void deleteById(UUID id) {
        animeMongoRepository.deleteById(id);
    }
}
