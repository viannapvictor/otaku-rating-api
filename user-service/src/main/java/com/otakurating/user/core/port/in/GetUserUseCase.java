package com.otakurating.user.core.port.in;

import com.otakurating.user.core.command.GetUserCommand;
import com.otakurating.user.core.model.KeycloakUserRepresentation;

public interface GetUserUseCase {
    KeycloakUserRepresentation execute(GetUserCommand command);
}