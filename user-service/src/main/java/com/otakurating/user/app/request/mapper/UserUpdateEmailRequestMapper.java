package com.otakurating.user.app.request.mapper;

import com.otakurating.user.app.request.dto.UserUpdateEmailRequestDTO;
import com.otakurating.user.core.model.UserUpdateEmailRequest;
import com.otakurating.user.core.model.valueobject.Email;
import org.springframework.stereotype.Component;

@Component
public class UserUpdateEmailRequestMapper {
    public UserUpdateEmailRequest toModel(UserUpdateEmailRequestDTO dto) {
        return new UserUpdateEmailRequest(
                Email.valueOf(dto.getEmail())
        );
    }
}