package com.otakurating.user.core.port.in;

import com.otakurating.user.core.command.UpdateUserCommand;
import com.otakurating.user.core.model.KeycloakUserRepresentation;

public interface UpdateUserUseCase {
    KeycloakUserRepresentation execute(UpdateUserCommand command);
}