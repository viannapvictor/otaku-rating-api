package com.otaku.rating.api.request.user.mapper;

import com.otaku.rating.api.request.user.dto.UserLoginDTO;
import com.otaku.rating.core.generic.mapper.InputMapper;
import com.otaku.rating.core.user.model.UserLogin;
import com.otaku.rating.core.user.model.valueobject.Email;
import com.otaku.rating.core.user.model.valueobject.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserLoginMapper implements InputMapper<UserLogin, UserLoginDTO> {
    @Override
    public UserLogin toModel(UserLoginDTO userLoginDTO) {
        return new UserLogin(
                Email.valueOf(userLoginDTO.getEmail()),
                Password.valueOf(userLoginDTO.getPassword())
        );
    }
}