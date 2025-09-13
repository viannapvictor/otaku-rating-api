package com.otaku.rating.api.request.user.mapper;

import com.otaku.rating.api.request.user.dto.UserRegisterDTO;
import com.otaku.rating.core.generic.mapper.InputMapper;
import com.otaku.rating.core.user.model.UserRegister;
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