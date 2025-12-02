package com.otakurating.rating.infra.adapter.messenger.adapter;

import com.otakurating.rating.core.event.ReviewDeletedEvent;
import com.otakurating.rating.infra.adapter.messenger.dto.ReviewSimpleEventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ReviewDeletedPublisher {
    private static final Logger log = LoggerFactory.getLogger(ReviewDeletedPublisher.class);
    private final StreamBridge streamBridge;

    public ReviewDeletedPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @EventListener
    public void publish(ReviewDeletedEvent event) {
        ReviewSimpleEventDTO dto = new ReviewSimpleEventDTO(
                event.getEventId(),
                event.getTimestamp(),
                event.getRatingId(),
                event.getUserId()
        );
        streamBridge.send("review-deleted-out-0", dto);
        log.info("Review deleted event sent to stream. ratingId={} | userId={}", event.getRatingId(), event.getUserId());
    }
}
