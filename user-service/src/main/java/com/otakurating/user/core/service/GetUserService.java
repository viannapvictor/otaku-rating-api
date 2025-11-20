package com.otakurating.user.core.service;

import com.otakurating.user.core.command.GetUserCommand;
import com.otakurating.user.core.model.KeycloakUserRepresentation;
import com.otakurating.user.core.port.in.GetUserUseCase;
import com.otakurating.user.core.port.out.UserAdminPort;
import org.springframework.stereotype.Service;

@Service
public class GetUserService implements GetUserUseCase {
    private final UserAdminPort userAdminPort;

    public GetUserService(UserAdminPort userAdminPort) {
        this.userAdminPort = userAdminPort;
    }

    @Override
    public KeycloakUserRepresentation execute(GetUserCommand command) {
        return userAdminPort.getUser(command.getUserId());
    }
}