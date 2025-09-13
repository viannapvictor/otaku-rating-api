package com.otaku.rating.api.request.user.mapper;

import com.otaku.rating.api.request.user.dto.UserUpdateEmailRequestDTO;
import com.otaku.rating.core.generic.mapper.InputMapper;
import com.otaku.rating.core.user.model.UserUpdateEmailRequest;
import com.otaku.rating.core.user.model.valueobject.Email;
import org.springframework.stereotype.Component;

@Component
public class UserUpdateEmailRequestMapper implements InputMapper<UserUpdateEmailRequest, UserUpdateEmailRequestDTO> {
    @Override
    public UserUpdateEmailRequest toModel(UserUpdateEmailRequestDTO dto) {
        return new UserUpdateEmailRequest(
                Email.valueOf(dto.getEmail())
        );
    }
}