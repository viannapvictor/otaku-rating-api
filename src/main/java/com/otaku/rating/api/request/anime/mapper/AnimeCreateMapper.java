package com.otaku.rating.api.request.anime.mapper;

import com.otaku.rating.api.request.anime.dto.AnimeCreateDTO;
import com.otaku.rating.core.anime.factory.AnimeFactory;
import com.otaku.rating.core.anime.factory.AnimeParams;
import com.otaku.rating.core.anime.model.Anime;
import com.otaku.rating.core.generic.mapper.InputMapper;
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
