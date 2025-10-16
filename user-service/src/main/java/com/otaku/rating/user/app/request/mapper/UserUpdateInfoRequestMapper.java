package com.otaku.rating.user.app.request.mapper;

import com.otaku.rating.generic.core.mapper.InputMapper;
import com.otaku.rating.user.app.request.dto.UserUpdateInfoRequestDTO;
import com.otaku.rating.user.core.model.UserUpdateInfoRequest;
import com.otaku.rating.user.core.model.valueobject.Name;
import com.otaku.rating.user.core.model.valueobject.UserName;
import org.springframework.stereotype.Component;

@Component
public class UserUpdateInfoRequestMapper implements InputMapper<UserUpdateInfoRequest, UserUpdateInfoRequestDTO> {
    @Override
    public UserUpdateInfoRequest toModel(UserUpdateInfoRequestDTO dto) {
        return new UserUpdateInfoRequest(
                Name.valueOf(dto.getName()),
                UserName.valueOf(dto.getUserName())
        );
    }
}