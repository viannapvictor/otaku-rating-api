package com.otaku.rating.api.request.anime.mapper;

import com.otaku.rating.api.request.anime.dto.AnimeCreateDTO;
import com.otaku.rating.core.anime.factory.AnimeFactory;
import com.otaku.rating.core.anime.factory.AnimeParams;
import com.otaku.rating.core.anime.model.Anime;
import com.otaku.rating.core.anime.factory.AnimeContributionParams;
import com.otaku.rating.core.generic.mapper.InputMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AnimeInputMapper implements InputMapper<Anime, AnimeCreateDTO> {
    private final AnimeFactory animeFactory;

    public Anime toModel(AnimeCreateDTO entity) {
        List<AnimeContributionParams> contributionParams = entity.getContributions()
                .stream()
                .map((c) -> new AnimeContributionParams(c.getId(), c.getRole()))
                .toList();
        AnimeParams params = new AnimeParams(
                entity.getTitle(),
                entity.getDescription(),
                entity.getRelease(),
                contributionParams
        );
        return animeFactory.create(params);
    }
}
