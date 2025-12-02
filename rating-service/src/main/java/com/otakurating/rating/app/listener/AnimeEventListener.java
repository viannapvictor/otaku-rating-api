package com.otakurating.rating.app.listener;

import com.otakurating.rating.app.request.dto.AnimeSimpleEventDTO;
import com.otakurating.rating.core.command.CreateRatingCommand;
import com.otakurating.rating.core.command.RemoveRatingCommand;
import com.otakurating.rating.core.port.in.CreateRatingUseCase;
import com.otakurating.rating.core.port.in.RemoveRatingUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class AnimeEventListener {
    private static final Logger log = LoggerFactory.getLogger(AnimeEventListener.class);
    private final CreateRatingUseCase createRatingUseCase;
    private final RemoveRatingUseCase removeRatingUseCase;

    public AnimeEventListener(
            CreateRatingUseCase createRatingUseCase,
            RemoveRatingUseCase removeRatingUseCase
    ) {
        this.createRatingUseCase = createRatingUseCase;
        this.removeRatingUseCase = removeRatingUseCase;
    }

    @Bean
    public Consumer<AnimeSimpleEventDTO> animeCreated() {
        return event -> {
            log.info("Anime created event received. animeId={}", event.animeId());
            CreateRatingCommand command = new CreateRatingCommand(event.animeId());
            createRatingUseCase.create(command);
        };
    }

    @Bean
    public Consumer<AnimeSimpleEventDTO> animeDeleted() {
        return event -> {
            log.info("Anime deleted event received. animeId={}", event.animeId());
            RemoveRatingCommand command = new RemoveRatingCommand(event.animeId());
            removeRatingUseCase.remove(command);
        };
    }
}
