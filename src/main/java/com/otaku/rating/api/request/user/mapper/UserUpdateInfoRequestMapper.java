package com.otaku.rating.api.request.user.mapper;

import com.otaku.rating.api.request.user.dto.UserUpdateInfoRequestDTO;
import com.otaku.rating.core.generic.exception.ValidationException;
import com.otaku.rating.core.generic.mapper.InputMapper;
import com.otaku.rating.core.user.model.UserUpdateInfoRequest;
import com.otaku.rating.core.user.model.valueobjects.Name;
import com.otaku.rating.core.user.model.valueobjects.UserName;
import org.springframework.stereotype.Component;

@Component
public class UserUpdateInfoRequestMapper implements InputMapper<UserUpdateInfoRequest, UserUpdateInfoRequestDTO> {

    @Override
    public UserUpdateInfoRequest toModel(UserUpdateInfoRequestDTO dto) throws ValidationException {
        return new UserUpdateInfoRequest(
                new Name(dto.getName()),
                new UserName(dto.getUserName())
        );
    }
}