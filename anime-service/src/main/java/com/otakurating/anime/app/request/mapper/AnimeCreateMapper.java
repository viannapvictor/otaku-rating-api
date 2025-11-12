package com.otakurating.anime.app.request.mapper;

import com.otakurating.anime.app.request.dto.AnimeCreateDTO;
import com.otakurating.anime.core.factory.AnimeFactory;
import com.otakurating.anime.core.factory.AnimeParams;
import com.otakurating.anime.core.model.Anime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnimeCreateMapper {
    private final AnimeFactory animeFactory;

    public Anime toModel(AnimeCreateDTO entity) {
        AnimeParams params = new AnimeParams(
                entity.getTitle(),
                entity.getDescription(),
                entity.getRelease()
        );
        return animeFactory.create(params);
    }
}
