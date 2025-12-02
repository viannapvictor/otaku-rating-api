package com.otakurating.rating.infra.adapter.messenger.adapter;

import com.otakurating.rating.core.event.ReviewCreatedEvent;
import com.otakurating.rating.infra.adapter.messenger.dto.ReviewSimpleEventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ReviewCreatedPublisher {
    private static final Logger log = LoggerFactory.getLogger(ReviewCreatedPublisher.class);
    private final StreamBridge streamBridge;

    public ReviewCreatedPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @EventListener
    public void publish(ReviewCreatedEvent event) {
        ReviewSimpleEventDTO dto = new ReviewSimpleEventDTO(
                event.getEventId(),
                event.getTimestamp(),
                event.getRatingId(),
                event.getUserId()
        );
        streamBridge.send("review-created-out-0", dto);
        log.info("Review created event sent to stream. ratingId={} | userId={}", event.getRatingId(), event.getUserId());
    }
}
