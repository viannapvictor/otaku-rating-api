package com.otaku.rating.infra.user.mapper;

import com.otaku.rating.core.generic.mapper.Mapper;
import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.model.valueobject.Email;
import com.otaku.rating.core.user.model.valueobject.Name;
import com.otaku.rating.core.user.model.valueobject.UserName;
import com.otaku.rating.infra.user.persistence.EmailConfirmationEntity;
import com.otaku.rating.infra.user.persistence.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserEntity> {
    @Override
    public User toModel(UserEntity userEntity) {
        return User.parseUnsafe(
                userEntity.getId(),
                Email.valueOfUnsafe(userEntity.getEmail()),
                UserName.valueOfUnsafe(userEntity.getUserName()),
                Name.valueOfUnsafe(userEntity.getName()),
                userEntity.getRole(),
                userEntity.getCreatedAt(),
                userEntity.getUpdatedAt()
        );
    }

    @Override
    public UserEntity toEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .userName(user.getUserName().getValue())
                .name(user.getName().getValue())
                .email(user.getEmail().getValue())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
