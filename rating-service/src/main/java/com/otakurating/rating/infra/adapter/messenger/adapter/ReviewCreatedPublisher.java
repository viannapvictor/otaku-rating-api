package com.otakurating.rating.infra.adapter.messenger.adapter;

import com.otakurating.rating.core.event.ReviewCreatedEvent;
import com.otakurating.rating.infra.adapter.messenger.dto.ReviewSimpleEventDTO;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ReviewCreatedPublisher {
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
    }
}
