package com.otakurating.user.core.service;

import com.otakurating.user.core.command.UpdateUserCommand;
import com.otakurating.user.core.model.KeycloakUserRepresentation;
import com.otakurating.user.core.port.in.UpdateUserUseCase;
import com.otakurating.user.core.port.out.UserAdminPort;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserService implements UpdateUserUseCase {
    private final UserAdminPort userAdminPort;

    public UpdateUserService(UserAdminPort userAdminPort) {
        this.userAdminPort = userAdminPort;
    }

    @Override
    public KeycloakUserRepresentation execute(UpdateUserCommand command) {
        KeycloakUserRepresentation updateRequest = new KeycloakUserRepresentation();
        updateRequest.setFirstName(command.getFirstName());
        updateRequest.setLastName(command.getLastName());
        updateRequest.setEmail(command.getEmail());

        userAdminPort.updateUser(command.getUserId(), updateRequest);

        return userAdminPort.getUser(command.getUserId());
    }
}