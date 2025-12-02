package com.otakurating.anime.core.port.out;

import com.otakurating.anime.core.model.Anime;
import com.otakurating.anime.core.strategy.AnimeSearchStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FindAnimePort {
    Optional<Anime> findById(UUID id);
    boolean existsById(UUID id);
    Page<Anime> findAnimePage(Pageable pageable, List<AnimeSearchStrategy> strategies);
}
