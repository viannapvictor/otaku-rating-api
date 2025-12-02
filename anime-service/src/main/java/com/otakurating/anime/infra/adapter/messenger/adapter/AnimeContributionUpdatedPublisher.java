package com.otakurating.anime.infra.adapter.messenger.adapter;

import com.otakurating.anime.core.event.AnimeContributionUpdatedEvent;
import com.otakurating.anime.infra.adapter.messenger.dto.AnimeContributionSimpleEventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AnimeContributionUpdatedPublisher {
    private static final Logger log = LoggerFactory.getLogger(AnimeContributionUpdatedPublisher.class);
    private final StreamBridge streamBridge;

    public AnimeContributionUpdatedPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @EventListener
    public void publish(AnimeContributionUpdatedEvent event) {
        AnimeContributionSimpleEventDTO dto = new AnimeContributionSimpleEventDTO(
                event.getEventId(),
                event.getTimestamp(),
                event.getAnimeId(),
                event.getPersonId()
        );
        streamBridge.send("anime-contribution-updated-out-0", dto);
        log.info("Anime contribution updated event sent to stream. animeId={} | personId={}", event.getAnimeId(), event.getPersonId());
    }
}
