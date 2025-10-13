package com.otaku.rating.user.infra.mapper;

import com.otaku.rating.generic.core.mapper.Mapper;
import com.otaku.rating.user.core.model.User;
import com.otaku.rating.user.core.model.valueobject.Email;
import com.otaku.rating.user.core.model.valueobject.Name;
import com.otaku.rating.user.core.model.valueobject.UserName;
import com.otaku.rating.user.infra.persistence.EmailConfirmationEntity;
import com.otaku.rating.user.infra.persistence.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserEntity> {
    @Override
    public User toModel(UserEntity userEntity) {
        EmailConfirmationEntity emailConfirmation = userEntity.getEmailConfirmation();
        boolean active = emailConfirmation == null || emailConfirmation.getNewEmail() != null;
        return User.parseUnsafe(
                userEntity.getId(),
                Email.valueOfUnsafe(userEntity.getEmail()),
                UserName.valueOfUnsafe(userEntity.getUserName()),
                Name.valueOfUnsafe(userEntity.getName()),
                userEntity.getPassword(),
                userEntity.getRole(),
                active,
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
                .password(user.getEncryptedPassword())
                .role(user.getRole())
                .active(user.isActive())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
