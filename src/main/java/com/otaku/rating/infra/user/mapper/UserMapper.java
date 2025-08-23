package com.otaku.rating.infra.user.mapper;

import com.otaku.rating.core.generic.mapper.Mapper;
import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.model.supportobjects.Email;
import com.otaku.rating.core.user.model.supportobjects.Name;
import com.otaku.rating.core.user.model.supportobjects.UserName;
import com.otaku.rating.infra.user.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserEntity> {
    @Override
    public User toModel(UserEntity userEntity) {
        return User.parseUnsafe(
                userEntity.getId(),
                Email.parseUnsafe(userEntity.getEmail()),
                UserName.parseUnsafe(userEntity.getUserName()),
                Name.parseUnsafe(userEntity.getName()),
                userEntity.getPassword(),
                userEntity.getRole(),
                userEntity.isActive()
        );
    }

    @Override
    public UserEntity toEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .userName(user.getUserName().getValue())
                .name(user.getName().getValue())
                .email(user.getEmail().getValue())
                .password(user.getEncryptedPassword())
                .role(user.getRole())
                .active(user.isActive())
                .build();
    }
}
