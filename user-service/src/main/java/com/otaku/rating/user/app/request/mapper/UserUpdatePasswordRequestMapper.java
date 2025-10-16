package com.otaku.rating.user.app.request.mapper;

import com.otaku.rating.generic.core.mapper.InputMapper;
import com.otaku.rating.user.app.request.dto.UserUpdatePasswordRequestDTO;
import com.otaku.rating.user.core.model.UserUpdatePasswordRequest;
import com.otaku.rating.user.core.model.valueobject.Password;
import org.springframework.stereotype.Component;

@Component
public class UserUpdatePasswordRequestMapper implements InputMapper<UserUpdatePasswordRequest, UserUpdatePasswordRequestDTO> {
    @Override
    public UserUpdatePasswordRequest toModel(UserUpdatePasswordRequestDTO dto) {
        return new UserUpdatePasswordRequest(
                Password.valueOf(dto.getOldPassword()),
                Password.valueOf(dto.getNewPassword())
        );
    }
}