package com.otakurating.anime.infra.adapter.messenger.adapter;

import com.otakurating.anime.core.event.AnimeCreatedEvent;
import com.otakurating.anime.infra.adapter.messenger.dto.AnimeSimpleEventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AnimeCreatedPublisher {
    private static final Logger log = LoggerFactory.getLogger(AnimeCreatedPublisher.class);
    private final StreamBridge streamBridge;

    public AnimeCreatedPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @EventListener
    public void publish(AnimeCreatedEvent event) {
        AnimeSimpleEventDTO dto = new AnimeSimpleEventDTO(
                event.getEventId(),
                event.getTimestamp(),
                event.getAnimeId()
        );
        streamBridge.send("anime-created-out-0", dto);
        log.info("Anime created event sent to stream. animeId={}", event.getAnimeId());
    }
}
