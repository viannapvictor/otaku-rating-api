package com.otakurating.rating.infra.adapter.messenger.adapter;

import com.otakurating.rating.core.event.RatingDeletedEvent;
import com.otakurating.rating.infra.adapter.messenger.dto.RatingSimpleEventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RatingDeletedPublisher {
    private static final Logger log = LoggerFactory.getLogger(RatingDeletedPublisher.class);
    private final StreamBridge streamBridge;

    public RatingDeletedPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @EventListener
    public void publish(RatingDeletedEvent event) {
        RatingSimpleEventDTO dto = new RatingSimpleEventDTO(
                event.getEventId(),
                event.getTimestamp(),
                event.getRatingId()
        );
        streamBridge.send("rating-deleted-out-0", dto);
        log.info("Rating deleted event sent to stream. ratingId={}", event.getRatingId());
    }
}
