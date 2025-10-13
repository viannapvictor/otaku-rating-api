package com.otaku.rating.user.app.request.mapper;

import com.otaku.rating.generic.core.mapper.InputMapper;
import com.otaku.rating.user.app.request.dto.UserRegisterDTO;
import com.otaku.rating.user.core.model.UserRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUserMapper implements InputMapper<UserRegister, UserRegisterDTO> {
    
    @Override
    public UserRegister toModel(UserRegisterDTO userRegisterDTO) {
        return userRegisterDTO.convertToEntity();
    }
}