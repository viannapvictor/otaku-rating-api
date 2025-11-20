package com.otakurating.user.core.port.in;

import com.otakurating.user.core.command.DeleteUserCommand;

public interface DeleteUserUseCase {
    void execute(DeleteUserCommand command);
}