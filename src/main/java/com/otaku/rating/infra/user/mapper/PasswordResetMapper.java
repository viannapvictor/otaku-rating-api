package com.otaku.rating.infra.user.mapper;
import com.otaku.rating.core.generic.mapper.Mapper;
import com.otaku.rating.core.user.model.PasswordReset;
import com.otaku.rating.core.user.model.valueobject.ConfirmationCode;
import com.otaku.rating.infra.user.persistence.PasswordResetEntity;
import com.otaku.rating.infra.user.persistence.UserEntity;
import org.springframework.stereotype.Component;

@Component
public final class PasswordResetMapper implements Mapper<PasswordReset, PasswordResetEntity> {
    @Override
    public PasswordReset toModel(PasswordResetEntity passwordResetEntity) {
        ConfirmationCode code = ConfirmationCode.valueOfUnsafe(passwordResetEntity.getCode());
        return PasswordReset.parseUnsafe(
                code,
                passwordResetEntity.getUser().getId(),
                passwordResetEntity.getExpiration()
        );
    }

    @Override
    public PasswordResetEntity toEntity(PasswordReset passwordReset) {
        return PasswordResetEntity.builder()
                .code(passwordReset.getCode().getValue())
                .expiration(passwordReset.getExpiration())
                .user(UserEntity.builder().id(passwordReset.getUserId()).build())
                .build();
    }
}
