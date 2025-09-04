package com.otaku.rating.infra.user.mapper;

import com.otaku.rating.core.generic.mapper.Mapper;
import com.otaku.rating.core.user.model.EmailConfirmation;
import com.otaku.rating.core.user.model.valueobjects.ConfirmationCode;
import com.otaku.rating.core.user.model.valueobjects.Email;
import com.otaku.rating.infra.user.entities.EmailConfirmationEntity;
import com.otaku.rating.infra.user.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class EmailConfirmationMapper implements Mapper<EmailConfirmation, EmailConfirmationEntity> {
    @Override
    public EmailConfirmation toModel(EmailConfirmationEntity emailConfirmationEntity) {
        String newEmail = emailConfirmationEntity.getNewEmail();
        return EmailConfirmation.parseUnsafe(
                ConfirmationCode.parseUnsafe(emailConfirmationEntity.getCode()),
                newEmail == null ? null : Email.parseUnsafe(newEmail),
                emailConfirmationEntity.getExpiration(),
                emailConfirmationEntity.getUser().getId()
        );
    }

    @Override
    public EmailConfirmationEntity toEntity(EmailConfirmation emailConfirmation) {
        Email newEmail = emailConfirmation.getNewEmail();
        return EmailConfirmationEntity.builder()
                .code(emailConfirmation.getCode().getValue())
                .expiration(emailConfirmation.getExpiration())
                .user(UserEntity.builder().id(emailConfirmation.getUserId()).build())
                .newEmail(newEmail == null ? null : newEmail.getValue())
                .build();
    }
}
