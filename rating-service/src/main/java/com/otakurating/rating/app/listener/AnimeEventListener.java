package com.otakurating.rating.app.listener;

import com.otakurating.rating.app.request.dto.AnimeSimpleEventDTO;
import com.otakurating.rating.core.command.CreateRatingCommand;
import com.otakurating.rating.core.command.RemoveRatingCommand;
import com.otakurating.rating.core.port.in.CreateRatingUseCase;
import com.otakurating.rating.core.port.in.RemoveRatingUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class AnimeEventListener {
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
            CreateRatingCommand command = new CreateRatingCommand(event.animeId());
            createRatingUseCase.create(command);
        };
    }

    @Bean
    public Consumer<AnimeSimpleEventDTO> animeDeleted() {
        return event -> {
            RemoveRatingCommand command = new RemoveRatingCommand(event.animeId());
            removeRatingUseCase.remove(command);
        };
    }
}
