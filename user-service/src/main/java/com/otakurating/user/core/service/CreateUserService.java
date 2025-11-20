package com.otakurating.user.core.service;

import com.otakurating.user.core.command.CreateUserCommand;
import com.otakurating.user.core.model.KeycloakUserRepresentation;
import com.otakurating.user.core.port.in.CreateUserUseCase;
import com.otakurating.user.core.port.out.UserAdminPort;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService implements CreateUserUseCase {
    private final UserAdminPort userAdminPort;

    public CreateUserService(UserAdminPort userAdminPort) {
        this.userAdminPort = userAdminPort;
    }

    @Override
    public KeycloakUserRepresentation execute(CreateUserCommand command) {
        KeycloakUserRepresentation keycloakUser = new KeycloakUserRepresentation();
        keycloakUser.setUsername(command.getUsername());
        keycloakUser.setEmail(command.getEmail());
        keycloakUser.setFirstName(command.getFirstName());
        keycloakUser.setLastName(command.getLastName());
        keycloakUser.setRoles(command.getRoles());
        keycloakUser.setEnabled(command.isEnabled());
        keycloakUser.setEmailVerified(command.isEmailVerified());

        String userId = userAdminPort.createUser(keycloakUser);

        if (command.getPassword() != null && !command.getPassword().isEmpty()) {
            userAdminPort.resetPassword(userId, command.getPassword(), false);
        }

        return userAdminPort.getUser(userId);
    }
}