package com.otaku.rating.api.request.user.mapper;

import com.otaku.rating.api.request.user.dto.UserUpdateInfoRequestDTO;
import com.otaku.rating.core.generic.mapper.InputMapper;
import com.otaku.rating.core.user.model.UserUpdateInfoRequest;
import com.otaku.rating.core.user.model.valueobject.Name;
import com.otaku.rating.core.user.model.valueobject.UserName;
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