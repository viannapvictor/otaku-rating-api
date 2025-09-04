package com.otaku.rating.infra.user.mapper;

import com.otaku.rating.core.generic.mapper.Mapper;
import com.otaku.rating.core.user.model.RefreshToken;
import com.otaku.rating.infra.user.entities.RefreshTokenEntity;
import com.otaku.rating.infra.user.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public final class RefreshTokenMapper implements Mapper<RefreshToken, RefreshTokenEntity> {
    @Override
    public RefreshToken toModel(RefreshTokenEntity refreshTokenEntity) {
        return RefreshToken.parseUnsafe(
                refreshTokenEntity.getCode(),
                refreshTokenEntity.getExpiration(),
                refreshTokenEntity.getUser().getId()
        );
    }

    @Override
    public RefreshTokenEntity toEntity(RefreshToken refreshToken) {
        return RefreshTokenEntity.builder()
                .code(refreshToken.getCode())
                .expiration(refreshToken.getExpiration())
                .user(UserEntity.builder().id(refreshToken.getUserId()).build())
                .build();
    }
}
