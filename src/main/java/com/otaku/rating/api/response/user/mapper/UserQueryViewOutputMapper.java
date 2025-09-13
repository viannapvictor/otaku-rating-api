package com.otaku.rating.api.response.user.mapper;

import com.otaku.rating.api.response.user.dto.UserQueryDTO;
import com.otaku.rating.api.response.user.dto.UserViewDTO;
import com.otaku.rating.core.generic.mapper.OutputMapper;
import com.otaku.rating.core.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserQueryViewOutputMapper implements OutputMapper<User, UserQueryDTO> {
    
    @Override
    public UserQueryDTO toEntity(User user) {
        return UserQueryDTO.parse(user, null);
    }
}