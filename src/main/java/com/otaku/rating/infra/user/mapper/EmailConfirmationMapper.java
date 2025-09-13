package com.otaku.rating.infra.user.mapper;

import com.otaku.rating.core.generic.mapper.Mapper;
import com.otaku.rating.core.user.model.EmailConfirmation;
import com.otaku.rating.core.user.model.valueobject.ConfirmationCode;
import com.otaku.rating.core.user.model.valueobject.Email;
import com.otaku.rating.infra.user.persistence.EmailConfirmationEntity;
import com.otaku.rating.infra.user.persistence.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class EmailConfirmationMapper implements Mapper<EmailConfirmation, EmailConfirmationEntity> {
    @Override
    public EmailConfirmation toModel(EmailConfirmationEntity emailConfirmationEntity) {
        String newEmail = emailConfirmationEntity.getNewEmail();
        return EmailConfirmation.parseUnsafe(
                ConfirmationCode.valueOfUnsafe(emailConfirmationEntity.getCode()),
                newEmail == null ? null : Email.valueOfUnsafe(newEmail),
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
