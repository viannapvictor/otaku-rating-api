package com.otakurating.rating.app.listener;

import com.otakurating.rating.app.request.dto.UserSimpleEventDTO;
import com.otakurating.rating.core.command.RemoveAllReviewFromUserCommand;
import com.otakurating.rating.core.port.in.RemoveAllReviewFromUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class UserEventListener {
    private final RemoveAllReviewFromUserUseCase removeAllReviewFromUserUseCase;

    public UserEventListener(RemoveAllReviewFromUserUseCase removeAllReviewFromUserUseCase) {
        this.removeAllReviewFromUserUseCase = removeAllReviewFromUserUseCase;
    }

    @Bean
    public Consumer<UserSimpleEventDTO> userDeleted() {
        return event -> {
            RemoveAllReviewFromUserCommand command = new RemoveAllReviewFromUserCommand(event.userId());
            removeAllReviewFromUserUseCase.remove(command);
        };
    }
}
