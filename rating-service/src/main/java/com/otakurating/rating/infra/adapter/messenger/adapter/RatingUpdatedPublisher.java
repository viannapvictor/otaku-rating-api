package com.otakurating.rating.infra.adapter.messenger.adapter;

import com.otakurating.rating.core.event.RatingUpdatedEvent;
import com.otakurating.rating.infra.adapter.messenger.dto.RatingSimpleEventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RatingUpdatedPublisher {
    private static final Logger log = LoggerFactory.getLogger(RatingUpdatedPublisher.class);
    private final StreamBridge streamBridge;

    public RatingUpdatedPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @EventListener
    public void publish(RatingUpdatedEvent event) {
        RatingSimpleEventDTO dto = new RatingSimpleEventDTO(
                event.getEventId(),
                event.getTimestamp(),
                event.getRatingId()
        );
        streamBridge.send("rating-updated-out-0", dto);
        log.info("Rating updated event sent to stream. ratingId={}", event.getRatingId());
    }
}
