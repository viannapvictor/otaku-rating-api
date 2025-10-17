package com.otaku.rating.core.anime.service;

import com.otaku.rating.core.anime.exception.AnimeAlreadyExistsException;
import com.otaku.rating.core.anime.exception.AnimeNotFoundException;
import com.otaku.rating.core.anime.model.Anime;
import com.otaku.rating.core.anime.repository.AnimeRepository;
import com.otaku.rating.core.anime.strategy.AnimeSearchStrategy;
import com.otaku.rating.core.generic.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AnimeServiceImpl implements AnimeService {
    public static final int MAX_PAGE_SIZE = 20;

    private final AnimeRepository animeRepository;

    @Override
    public Page<Anime> getPage(int page, int size, List<AnimeSearchStrategy> strategies) {
        Pageable pageable = PageUtils.createPageable(page, size, MAX_PAGE_SIZE);
        return animeRepository.findAnimePage(pageable, strategies);
    }

    @Override
    public Anime add(Anime anime) {
        Objects.requireNonNull(anime);
        if (animeRepository.exists(anime.getId().getValue())) {
            throw new AnimeAlreadyExistsException();
        }
        return animeRepository.save(anime);
    }

    @Override
    @Transactional
    public Anime update(String oldId, Anime anime) {
        Objects.requireNonNull(anime);
        Anime oldAnime = getById(oldId);
        if (Objects.equals(oldId, anime.getId().getValue())) {
            return animeRepository.save(anime);
        }
        animeRepository.delete(oldAnime);
        return animeRepository.save(anime);
    }

    @Override
    public void delete(Anime anime) {
        Objects.requireNonNull(anime);
        animeRepository.delete(anime);
    }

    @Override
    public Anime getById(String id) {
        return animeRepository.findById(id).orElseThrow(AnimeNotFoundException::new);
    }
}
