package com.otaku.rating.api.request.user.mapper;

import com.otaku.rating.api.request.user.dto.UserUpdateEmailRequestDTO;
import com.otaku.rating.core.generic.exception.ValidationException;
import com.otaku.rating.core.generic.mapper.InputMapper;
import com.otaku.rating.core.user.model.UserUpdateEmailRequest;
import com.otaku.rating.core.user.model.valueobjects.Email;
import org.springframework.stereotype.Component;

@Component
public class UserUpdateEmailRequestMapper implements InputMapper<UserUpdateEmailRequest, UserUpdateEmailRequestDTO> {

    @Override
    public UserUpdateEmailRequest toModel(UserUpdateEmailRequestDTO dto) throws ValidationException {
        return new UserUpdateEmailRequest(
                new Email(dto.getEmail())
        );
    }
}