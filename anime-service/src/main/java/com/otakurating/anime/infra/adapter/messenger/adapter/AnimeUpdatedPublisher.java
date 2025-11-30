package com.otakurating.anime.infra.adapter.messenger.adapter;

import com.otakurating.anime.core.event.AnimeUpdatedEvent;
import com.otakurating.anime.infra.adapter.messenger.dto.AnimeSimpleEventDTO;
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
        AnimeSimpleEventDTO dto = new AnimeSimpleEventDTO(
                event.getEventId(),
                event.getTimestamp(),
                event.getAnimeId()
        );
        streamBridge.send("anime-updated-out-0", dto);
    }
}
