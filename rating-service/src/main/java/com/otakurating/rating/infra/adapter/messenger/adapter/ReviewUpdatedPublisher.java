package com.otakurating.rating.infra.adapter.messenger.adapter;

import com.otakurating.rating.core.event.ReviewUpdatedEvent;
import com.otakurating.rating.infra.adapter.messenger.dto.ReviewSimpleEventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ReviewUpdatedPublisher {
    private static final Logger log = LoggerFactory.getLogger(ReviewUpdatedPublisher.class);
    private final StreamBridge streamBridge;

    public ReviewUpdatedPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @EventListener
    public void publish(ReviewUpdatedEvent event) {
        ReviewSimpleEventDTO dto = new ReviewSimpleEventDTO(
                event.getEventId(),
                event.getTimestamp(),
                event.getRatingId(),
                event.getUserId()
        );
        streamBridge.send("review-updated-out-0", dto);
        log.info("Review updated event sent to stream. ratingId={} | userId={}", event.getRatingId(), event.getUserId());
    }
}
