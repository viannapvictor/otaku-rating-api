package com.otakurating.anime.infra.adapter.persistence.adapter;

import com.otakurating.anime.core.port.out.DeleteAnimePort;
import com.otakurating.anime.infra.adapter.persistence.repository.AnimeMongoRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteAnimeAdapter implements DeleteAnimePort {
    private final AnimeMongoRepository animeMongoRepository;

    public DeleteAnimeAdapter(AnimeMongoRepository animeMongoRepository) {
        this.animeMongoRepository = animeMongoRepository;
    }

    @Override
    public void deleteById(String id) {
        animeMongoRepository.deleteById(id);
    }
}
