package com.otakurating.anime.core.port.out;

import com.otakurating.anime.core.model.Anime;
import com.otakurating.anime.core.strategy.AnimeSearchStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface FindAnimePort {
    Optional<Anime> findById(String id);
    boolean existsById(String id);
    Page<Anime> findAnimePage(Pageable pageable, List<AnimeSearchStrategy> strategies);
}
