package com.otakurating.rating.infra.adapter.messenger.adapter;

import com.otakurating.rating.core.event.RatingCreatedEvent;
import com.otakurating.rating.infra.adapter.messenger.dto.RatingSimpleEventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RatingCreatedPublisher {
    private static final Logger log = LoggerFactory.getLogger(RatingCreatedPublisher.class);
    private final StreamBridge streamBridge;

    public RatingCreatedPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @EventListener
    public void publish(RatingCreatedEvent event) {
        RatingSimpleEventDTO dto = new RatingSimpleEventDTO(
                event.getEventId(),
                event.getTimestamp(),
                event.getRatingId()
        );
        streamBridge.send("rating-created-out-0", dto);
        log.info("Rating created event sent to stream. ratingId={}", event.getRatingId());
    }
}
