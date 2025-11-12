package com.otakurating.user.app.request.mapper;

import com.otakurating.user.app.request.dto.UserUpdatePasswordRequestDTO;
import com.otakurating.user.core.model.UserUpdatePasswordRequest;
import com.otakurating.user.core.model.valueobject.Password;
import org.springframework.stereotype.Component;

@Component
public class UserUpdatePasswordRequestMapper {
    public UserUpdatePasswordRequest toModel(UserUpdatePasswordRequestDTO dto) {
        return new UserUpdatePasswordRequest(
                Password.valueOf(dto.getOldPassword()),
                Password.valueOf(dto.getNewPassword())
        );
    }
}