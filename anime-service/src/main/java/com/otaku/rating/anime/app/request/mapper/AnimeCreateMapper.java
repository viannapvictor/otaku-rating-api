package com.otaku.rating.anime.app.request.mapper;

import com.otaku.rating.anime.app.request.dto.AnimeCreateDTO;
import com.otaku.rating.anime.core.factory.AnimeFactory;
import com.otaku.rating.anime.core.factory.AnimeParams;
import com.otaku.rating.anime.core.model.Anime;
import com.otaku.rating.generic.core.mapper.InputMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnimeCreateMapper implements InputMapper<Anime, AnimeCreateDTO> {
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
