package com.otakurating.user.core.service;

import com.otakurating.user.core.command.DeleteUserCommand;
import com.otakurating.user.core.event.UserDeletedEvent;
import com.otakurating.user.core.model.KeycloakUserRepresentation;
import com.otakurating.user.core.model.User;
import com.otakurating.user.core.port.in.DeleteUserUseCase;
import com.otakurating.user.core.port.out.EventPublisherPort;
import com.otakurating.user.core.port.out.EventStorePort;
import com.otakurating.user.core.port.out.UserAdminPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteUserService implements DeleteUserUseCase {
    private final UserAdminPort userAdminPort;
    private final EventPublisherPort eventPublisherPort;
    private final EventStorePort eventStorePort;

    public DeleteUserService(UserAdminPort userAdminPort, EventPublisherPort eventPublisherPort, EventStorePort eventStorePort) {
        this.userAdminPort = userAdminPort;
        this.eventPublisherPort = eventPublisherPort;
        this.eventStorePort = eventStorePort;
    }

    @Override
    public void execute(DeleteUserCommand command) {
        KeycloakUserRepresentation keycloakUser = userAdminPort.getUser(command.getUserId());

        User user = new User(
            keycloakUser.getId(),
            keycloakUser.getUsername(),
            keycloakUser.getEmail(),
            keycloakUser.getFirstName(),
            keycloakUser.getLastName()
        );

        user.delete();

        for (UserDeletedEvent event : user.pullEvents()) {
            storeAndPublishEvent(event, user.getId());
        }

        userAdminPort.deleteUser(command.getUserId());
    }

    @Transactional
    protected void storeAndPublishEvent(UserDeletedEvent event, String userId) {
        eventStorePort.store(event, "User", userId);
        eventPublisherPort.publish(event);
    }
}