package com.otaku.rating.user.app.request.mapper;

import com.otaku.rating.generic.core.mapper.InputMapper;
import com.otaku.rating.user.app.request.dto.UserLoginDTO;
import com.otaku.rating.user.core.model.UserLogin;
import com.otaku.rating.user.core.model.valueobject.Email;
import com.otaku.rating.user.core.model.valueobject.Password;
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