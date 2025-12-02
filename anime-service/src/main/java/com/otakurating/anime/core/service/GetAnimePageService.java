package com.otakurating.anime.core.service;

import com.otakurating.anime.core.command.GetAnimePageCommand;
import com.otakurating.anime.core.model.Anime;
import com.otakurating.anime.core.port.in.GetAnimePageUseCase;
import com.otakurating.anime.core.port.out.FindAnimePort;
import com.otakurating.anime.core.strategy.AnimeSearchStrategy;
import com.otakurating.anime.core.strategy.DescriptionAnimeSearchStrategy;
import com.otakurating.anime.core.strategy.TitleAnimeSearchStrategy;
import com.otakurating.anime.core.utils.PageUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAnimePageService implements GetAnimePageUseCase {
    public static final int MAX_PAGE_SIZE = 20;

    private final FindAnimePort findAnimePort;

    public GetAnimePageService(FindAnimePort findAnimePort) {
        this.findAnimePort = findAnimePort;
    }

    @Override
    public Page<Anime> getPage(GetAnimePageCommand command) {
        Pageable pageable = PageUtils.createPageable(command.getPage(), command.getSize(), MAX_PAGE_SIZE);
        List<AnimeSearchStrategy> strategies = new ArrayList<>();
        if (command.getTitle() != null && !command.getTitle().isBlank()) {
            strategies.add(new TitleAnimeSearchStrategy(command.getTitle()));
        }
        if (command.getDescription() != null && !command.getDescription().isBlank()) {
            strategies.add(new DescriptionAnimeSearchStrategy(command.getDescription()));
        }
        return findAnimePort.findAnimePage(pageable, strategies);
    }
}
