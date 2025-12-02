package com.otakurating.rating.infra.adapter.messenger.adapter;

import com.otakurating.rating.core.event.UserReviewsDeletedEvent;
import com.otakurating.rating.infra.adapter.messenger.dto.UserReviewsDeletedEventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserReviewsDeletedPublisher {
    private static final Logger log = LoggerFactory.getLogger(UserReviewsDeletedPublisher.class);
    private final StreamBridge streamBridge;

    public UserReviewsDeletedPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @EventListener
    public void publish(UserReviewsDeletedEvent event) {
        UserReviewsDeletedEventDTO dto = new UserReviewsDeletedEventDTO(
                event.getEventId(),
                event.getTimestamp(),
                event.getUserId()
        );
        streamBridge.send("user-reviews-deleted-out-0", dto);
        log.info("User reviews deleted event sent to stream. userId={}", event.getUserId());
    }
}
