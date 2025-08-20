package com.otaku.rating.core.anime.service;

import com.otaku.rating.core.anime.model.Anime;
import com.otaku.rating.core.anime.repository.AnimeRepository;
import com.otaku.rating.core.generic.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimeServiceImpl implements AnimeService {
    private final AnimeRepository animeRepository;

    // TODO: Implementar getPage
    @Override
    public Page<Anime> getPage(int page, int size) {
        return null;
    }

    @Override
    public Anime add(Anime anime) {
        if (anime == null) {
            throw new IllegalArgumentException("The anime object must not be null.");
        }
        if (animeRepository.exists(anime.getId().getValue())) {
            throw new ConflictException("anime.id.conflict", "An anime with the generated id already exists.");
        }
        return animeRepository.save(anime);
    }

    @Override
    public Anime update(String oldId, Anime anime) {
        if (anime == null) {
            throw new IllegalArgumentException("The anime object must not be null.");
        }
        if (!animeRepository.exists(anime.getId().getValue())) {
            throw new NotFoundException("anime.not.found", "Could not find an anime with the specified id to update.");
        }
    }

    @Override
    public void delete(Anime anime) {

    }

    @Override
    public Anime getById(String id) {
        return null;
    }
}
