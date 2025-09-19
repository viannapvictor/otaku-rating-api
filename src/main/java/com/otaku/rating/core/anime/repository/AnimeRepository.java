package com.otaku.rating.core.anime.repository;

import com.otaku.rating.core.anime.model.Anime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AnimeRepository {
    Anime save(Anime anime);
    boolean exists(String id);
    Optional<Anime> findById(String id);
    Page<Anime> findAnimePage(Pageable pageable);
    void delete(Anime anime);
}
