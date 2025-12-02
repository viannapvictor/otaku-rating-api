package com.otakurating.rating.app.listener;

import com.otakurating.rating.app.request.dto.UserSimpleEventDTO;
import com.otakurating.rating.core.command.RemoveAllReviewFromUserCommand;
import com.otakurating.rating.core.port.in.RemoveAllReviewFromUserUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class UserEventListener {
    private static final Logger log = LoggerFactory.getLogger(UserEventListener.class);
    private final RemoveAllReviewFromUserUseCase removeAllReviewFromUserUseCase;

    public UserEventListener(RemoveAllReviewFromUserUseCase removeAllReviewFromUserUseCase) {
        this.removeAllReviewFromUserUseCase = removeAllReviewFromUserUseCase;
    }

    @Bean
    public Consumer<UserSimpleEventDTO> userDeleted() {
        return event -> {
            log.info("User deleted event received. userId={}", event.userId());
            RemoveAllReviewFromUserCommand command = new RemoveAllReviewFromUserCommand(event.userId());
            removeAllReviewFromUserUseCase.remove(command);
        };
    }
}
