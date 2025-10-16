package com.otaku.rating.user.infra.mapper;

import com.otaku.rating.generic.core.mapper.Mapper;
import com.otaku.rating.user.core.model.RefreshToken;
import com.otaku.rating.user.infra.persistence.RefreshTokenEntity;
import com.otaku.rating.user.infra.persistence.UserEntity;
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
