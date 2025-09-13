package com.otaku.rating.api.request.user.mapper;

import com.otaku.rating.api.request.user.dto.UserUpdatePasswordRequestDTO;
import com.otaku.rating.core.generic.mapper.InputMapper;
import com.otaku.rating.core.user.model.UserUpdatePasswordRequest;
import com.otaku.rating.core.user.model.valueobject.Password;
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