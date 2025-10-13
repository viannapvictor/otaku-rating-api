package com.otaku.rating.user.app.response.mapper;

import com.otaku.rating.generic.core.mapper.OutputMapper;
import com.otaku.rating.user.app.response.dto.UserViewDTO;
import com.otaku.rating.user.core.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserViewOutputMapper implements OutputMapper<User, UserViewDTO> {
    
    @Override
    public UserViewDTO toEntity(User user) {
        return new UserViewDTO(
                user.getId(),
                user.getUserName().getValue(),
                user.getName().getValue(),
                user.getEmail().getValue(),
                user.getRole(),
                user.isActive(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}