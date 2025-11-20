package com.otakurating.user.core.port.in;

import com.otakurating.user.core.command.CreateUserCommand;
import com.otakurating.user.core.model.KeycloakUserRepresentation;

public interface CreateUserUseCase {
    KeycloakUserRepresentation execute(CreateUserCommand command);
}