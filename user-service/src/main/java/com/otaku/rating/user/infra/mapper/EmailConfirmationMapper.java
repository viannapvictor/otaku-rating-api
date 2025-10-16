package com.otaku.rating.user.infra.mapper;

import com.otaku.rating.generic.core.mapper.Mapper;
import com.otaku.rating.user.core.model.EmailConfirmation;
import com.otaku.rating.user.core.model.valueobject.ConfirmationCode;
import com.otaku.rating.user.core.model.valueobject.Email;
import com.otaku.rating.user.infra.persistence.EmailConfirmationEntity;
import com.otaku.rating.user.infra.persistence.UserEntity;
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
