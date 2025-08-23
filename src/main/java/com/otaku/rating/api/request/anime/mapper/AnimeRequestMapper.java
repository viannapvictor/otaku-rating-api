package com.otaku.rating.api.request.anime.mapper;

import com.otaku.rating.api.request.anime.dto.AnimeCreateDTO;
import com.otaku.rating.core.anime.factory.AnimeFactory;
import com.otaku.rating.core.anime.factory.AnimeParams;
import com.otaku.rating.core.anime.model.Anime;
import com.otaku.rating.core.anime.factory.AnimeContributionParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AnimeRequestMapper {
    private final AnimeFactory animeFactory;

    public Anime toModel(AnimeCreateDTO form) {
        List<AnimeContributionParams> contributionParams = form.getContributions()
                .stream()
                .map((c) -> new AnimeContributionParams(c.getId(), c.getRole()))
                .toList();
        AnimeParams params = new AnimeParams(form.getTitle(), form.getDescription(), form.getRelease(), contributionParams);
        return animeFactory.create(params);
    }
}
