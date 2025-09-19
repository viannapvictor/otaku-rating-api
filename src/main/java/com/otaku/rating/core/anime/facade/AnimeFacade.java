package com.otaku.rating.core.anime.facade;

import com.otaku.rating.core.anime.model.Anime;
import org.springframework.data.domain.Page;

public interface AnimeFacade {
    Page<Anime> getPage(int page, int size);
    Anime add(Anime anime);
    Anime update(String oldId, Anime anime);
    void delete(Anime anime);
    Anime getById(String id);
}
