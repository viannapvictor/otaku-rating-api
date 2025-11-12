package com.otakurating.anime.core.repository;

import com.otakurating.anime.core.model.Anime;
import com.otakurating.anime.core.strategy.AnimeSearchStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AnimeRepository {
    Anime save(Anime anime);
    boolean exists(String id);
    Optional<Anime> findById(String id);
    Page<Anime> findAnimePage(Pageable pageable, List<AnimeSearchStrategy> strategies);
    void delete(Anime anime);
}
