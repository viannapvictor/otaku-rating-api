package com.otaku.rating.user.app.request.mapper;

import com.otaku.rating.generic.core.mapper.InputMapper;
import com.otaku.rating.user.app.request.dto.UserUpdateEmailRequestDTO;
import com.otaku.rating.user.core.model.UserUpdateEmailRequest;
import com.otaku.rating.user.core.model.valueobject.Email;
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