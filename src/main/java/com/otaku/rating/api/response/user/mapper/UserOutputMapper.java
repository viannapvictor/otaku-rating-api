package com.otaku.rating.api.response.user.mapper;

import com.otaku.rating.api.response.user.dto.UserViewDTO;
import com.otaku.rating.core.generic.mapper.OutputMapper;
import com.otaku.rating.core.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserOutputMapper implements OutputMapper<User, UserViewDTO> {
    
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