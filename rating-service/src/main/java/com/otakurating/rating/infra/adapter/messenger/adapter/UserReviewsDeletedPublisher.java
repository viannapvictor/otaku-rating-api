package com.otakurating.rating.infra.adapter.messenger.adapter;

import com.otakurating.rating.core.event.UserReviewsDeletedEvent;
import com.otakurating.rating.infra.adapter.messenger.dto.UserReviewsDeletedEventDTO;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserReviewsDeletedPublisher {
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
    }
}
