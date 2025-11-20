package com.otakurating.anime.infra.adapter.messenger.adapter;

import com.otakurating.anime.core.event.AnimeDeletedEvent;
import com.otakurating.anime.infra.adapter.messenger.dto.AnimeSimpleEventDTO;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AnimeDeletedPublisher {
    private final StreamBridge streamBridge;

    public AnimeDeletedPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @EventListener
    public void publish(AnimeDeletedEvent event) {
        AnimeSimpleEventDTO dto = new AnimeSimpleEventDTO(
                event.getEventId(),
                event.getTimestamp(),
                event.getAnimeId()
        );
        streamBridge.send("anime-deleted-out-0", dto);
    }
}
