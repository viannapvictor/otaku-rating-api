package com.otaku.rating.api.request.user.mapper;

import com.otaku.rating.api.request.user.dto.UserLoginDTO;
import com.otaku.rating.core.generic.exception.ValidationException;
import com.otaku.rating.core.generic.mapper.InputMapper;
import com.otaku.rating.core.user.model.UserLogin;
import com.otaku.rating.core.user.model.valueobjects.Email;
import com.otaku.rating.core.user.model.valueobjects.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserLoginMapper implements InputMapper<UserLogin, UserLoginDTO> {
    
    @Override
    public UserLogin toModel(UserLoginDTO userLoginDTO) throws ValidationException {
        return new UserLogin(
                new Email(userLoginDTO.getEmail()),
                new Password(userLoginDTO.getPassword())
        );
    }
}