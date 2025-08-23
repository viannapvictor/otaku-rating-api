package com.otaku.rating.api.request.user.mapper;

import com.otaku.rating.api.request.user.dto.UserRegisterDTO;
import com.otaku.rating.core.generic.exception.ValidationException;
import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.model.UserRegister;
import com.otaku.rating.core.user.service.PasswordEncoderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRequestMapper {
    private final PasswordEncoderServiceImpl passwordEncoderService;
    
    public User toModel(UserRegisterDTO userRegisterDTO) throws ValidationException {
        UserRegister userRegister = userRegisterDTO.convertToEntity();
        return new User(passwordEncoderService, userRegister);
    }
}