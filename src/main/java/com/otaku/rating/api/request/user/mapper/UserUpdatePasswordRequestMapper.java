package com.otaku.rating.api.request.user.mapper;

import com.otaku.rating.api.request.user.dto.UserUpdatePasswordRequestDTO;
import com.otaku.rating.core.generic.exception.ValidationException;
import com.otaku.rating.core.generic.mapper.InputMapper;
import com.otaku.rating.core.user.model.UserUpdatePasswordRequest;
import com.otaku.rating.core.user.model.valueobjects.Password;
import org.springframework.stereotype.Component;

@Component
public class UserUpdatePasswordRequestMapper implements InputMapper<UserUpdatePasswordRequest, UserUpdatePasswordRequestDTO> {

    @Override
    public UserUpdatePasswordRequest toModel(UserUpdatePasswordRequestDTO dto) throws ValidationException {
        return new UserUpdatePasswordRequest(
                new Password(dto.getOldPassword()),
                new Password(dto.getNewPassword())
        );
    }
}