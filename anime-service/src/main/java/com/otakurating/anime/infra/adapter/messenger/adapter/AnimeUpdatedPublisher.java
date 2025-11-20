package com.otakurating.anime.infra.adapter.messenger.adapter;

import com.otakurating.anime.core.event.AnimeUpdatedEvent;
import com.otakurating.anime.infra.adapter.messenger.dto.AnimeUpdatedEventDTO;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AnimeUpdatedPublisher {
    private final StreamBridge streamBridge;

    public AnimeUpdatedPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @EventListener
    public void publish(AnimeUpdatedEvent event) {
        AnimeUpdatedEventDTO dto = new AnimeUpdatedEventDTO(
                event.getEventId(),
                event.getTimestamp(),
                event.getOldAnimeId(),
                event.getAnimeId()
        );
        streamBridge.send("anime-updated-out-0", dto);
    }
}
