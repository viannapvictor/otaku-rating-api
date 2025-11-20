package com.otakurating.anime.infra.adapter.messenger.adapter;

import com.otakurating.anime.core.event.AnimeContributionCreatedEvent;
import com.otakurating.anime.infra.adapter.messenger.dto.AnimeContributionSimpleEventDTO;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AnimeContributionCreatedPublisher {
    private final StreamBridge streamBridge;

    public AnimeContributionCreatedPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @EventListener
    public void publish(AnimeContributionCreatedEvent event) {
        AnimeContributionSimpleEventDTO dto = new AnimeContributionSimpleEventDTO(
                event.getEventId(),
                event.getTimestamp(),
                event.getAnimeId(),
                event.getPersonId()
        );
        streamBridge.send("anime-contribution-created-out-0", dto);
    }
}
