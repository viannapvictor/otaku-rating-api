package com.otakurating.rating.infra.adapter.messenger.adapter;

import com.otakurating.rating.core.event.ReviewUpdatedEvent;
import com.otakurating.rating.infra.adapter.messenger.dto.ReviewSimpleEventDTO;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ReviewUpdatedPublisher {
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
    }
}
