package com.otaku.rating.user.app.response.mapper;

import com.otaku.rating.generic.core.mapper.OutputMapper;
import com.otaku.rating.user.app.response.dto.UserQueryDTO;
import com.otaku.rating.user.core.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserQueryViewOutputMapper implements OutputMapper<User, UserQueryDTO> {
    
    @Override
    public UserQueryDTO toEntity(User user) {
        return UserQueryDTO.parse(user, null);
    }
}